package com.example.abc2019sconferenceapp.fragment.search;

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.abc2019sconferenceapp.R
import com.example.abc2019sconferenceapp.databinding.FragmentSearchBinding
import com.example.abc2019sconferenceapp.fragment.SearchResultFragment
import com.example.abc2019sconferenceapp.fragment.search.domain.SearchHistoryReadonlyRepository
import com.example.abc2019sconferenceapp.fragment.search.domain.SearchHistoryRepository
import com.example.abc2019sconferenceapp.fragment.search.domain.entity.SearchHistoryEntity
import com.example.abc2019sconferenceapp.fragment.search.infra.SearchHistoryRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class SearchFragment : Fragment() {

  val searchHistoryReadonlyRepository: SearchHistoryReadonlyRepository = SearchHistoryRepositoryImpl(requireContext())
  val searchHistoryRepository: SearchHistoryRepository = SearchHistoryRepositoryImpl(requireContext())

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val binding = FragmentSearchBinding.inflate(
      inflater,
      container,
      false
    )

    binding.searchButton.setOnClickListener {

      GlobalScope.launch {
        try {
          val searchText = binding.textView.text.toString()
          val searchHistory = withContext(Dispatchers.IO) { searchHistoryReadonlyRepository.loadSearchHistry() }.convert()

          fun nextSearchHistoryId(histryList: List<SearchHistoryBindingItem>) = histryList.map { it.id + 1 }.max()
            ?: 0

          searchHistoryRepository.addSearchHistory(
            SearchHistoryEntity(
              nextSearchHistoryId(searchHistory),
              searchText
            )
          )
        } catch (e: Exception) {
          Toast.makeText(requireContext(), "検索履歴の追加に失敗しました", Toast.LENGTH_SHORT).show()
        }
      }

      requireActivity()
        .supportFragmentManager
        .beginTransaction()
        .replace(
          R.id.setFragmentLayout,
          SearchResultFragment()
        )
        .commit()
    }

    GlobalScope.launch {
      try {
        val searchHistory = withContext(Dispatchers.IO) { searchHistoryReadonlyRepository.loadSearchHistry() }.convert()
        val searchHistoryAdapter = SearchHistoryAdapter(searchHistory)

        searchHistoryAdapter.searchHistoryItemClickListener = object : SearchHistoryItemClickListener {
          override fun onClick(searchHistoryBindingItem: SearchHistoryBindingItem) {
            binding.textView.text = searchHistoryBindingItem.queryText
          }
        }

        binding.searchHistoryRecyclerView.adapter = searchHistoryAdapter
      } catch (e: Exception) {
        Toast.makeText(requireContext(), "検索履歴の取得に失敗しました", Toast.LENGTH_SHORT).show()
      }
    }

    return binding.root
  }
}
