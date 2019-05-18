package jagsc.jp.abc2019Sconferenceapp.fragment.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import jagsc.jp.abc2019Sconferenceapp.R
import jagsc.jp.abc2019Sconferenceapp.fragment.SearchResultFragment
import jagsc.jp.abc2019Sconferenceapp.fragment.search.domain.SearchHistoryReadonlyRepository
import jagsc.jp.abc2019Sconferenceapp.fragment.search.domain.SearchHistoryRepository
import jagsc.jp.abc2019Sconferenceapp.fragment.search.domain.entity.SearchHistoryEntity
import jagsc.jp.abc2019Sconferenceapp.fragment.search.infra.SearchHistoryRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class SearchFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val binding = jagsc.jp.abc2019Sconferenceapp.databinding.FragmentSearchBinding.inflate(
      inflater,
      container,
      false
    )

    val searchHistoryReadonlyRepository: SearchHistoryReadonlyRepository = SearchHistoryRepositoryImpl(requireContext())
    val searchHistoryRepository: SearchHistoryRepository = SearchHistoryRepositoryImpl(requireContext())

    binding.searchButton.setOnClickListener {

      GlobalScope.launch(Dispatchers.Main) {
        try {
          val searchText = binding.searchBar.text.toString()
          if (searchText.isEmpty()) {
            return@launch
          }
          val searchHistory = withContext(Dispatchers.IO) { searchHistoryReadonlyRepository.loadSearchHistry() }.convert()

          fun nextSearchHistoryId(histryList: List<SearchHistoryBindingItem>) = histryList.map { it.id + 1 }.max()
            ?: 0

          searchHistoryRepository.addSearchHistory(
            SearchHistoryEntity(
              nextSearchHistoryId(searchHistory),
              searchText
            )
          )
        } catch (e : java.lang.Exception) {
          e.printStackTrace()
          Toast.makeText(requireContext(), "検索履歴の追加に失敗しました", Toast.LENGTH_SHORT).show()
        }
      }

      val manager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      manager.hideSoftInputFromWindow(view?.windowToken, 0)

      requireActivity()
        .supportFragmentManager
        .beginTransaction()
        .addToBackStack(null)
        .replace(
          R.id.searchLayout,
          SearchResultFragment()
        )
        .commitAllowingStateLoss()
    }

    GlobalScope.launch(Dispatchers.Main) {
      val context = requireContext()
      try {
        val searchHistory = withContext(Dispatchers.IO) { searchHistoryReadonlyRepository.loadSearchHistry() }.convert()
        val searchHistoryList = searchHistory.distinctBy { it.queryText }//重複を削除した結果をsearchHistoryListに代入する
        val searchHistoryAdapter = SearchHistoryAdapter()
        searchHistoryAdapter.submitList(searchHistoryList)

        searchHistoryAdapter.searchHistoryItemClickListener = object : SearchHistoryItemClickListener {
          override fun onClick(searchHistoryBindingItem: SearchHistoryBindingItem) {
            if (searchHistoryBindingItem.queryText.isEmpty()) {
              return
            } else {
              binding.searchBar.text.append(searchHistoryBindingItem.queryText)
            }
          }
        }

        binding.searchHistoryRecyclerView.adapter = searchHistoryAdapter
        binding.searchHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
      } catch (e: Exception) {
        Toast.makeText(context, "検索履歴の取得に失敗しました", Toast.LENGTH_SHORT).show()
      }
    }

    binding.clearSearchHistoryMaterialButton.setOnClickListener {
      GlobalScope.launch(Dispatchers.IO) {
        searchHistoryRepository.clearSearchHistory()
        val adapter = binding.searchHistoryRecyclerView.adapter
        val searchHistory = withContext(Dispatchers.IO) { searchHistoryReadonlyRepository.loadSearchHistry() }.convert()
        (adapter as ListAdapter<SearchHistoryBindingItem, SearchHistoryViewHolder>).submitList(searchHistory)
      }
    }

    return binding.root
  }
}
