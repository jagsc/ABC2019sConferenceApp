package com.example.abc2019sconferenceapp.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.abc2019sconferenceapp.R
import com.example.abc2019sconferenceapp.fragment.SearchResultFragment
import com.example.abc2019sconferenceapp.fragment.search.domain.SearchHistoryReadonlyRepository
import com.example.abc2019sconferenceapp.fragment.search.domain.SearchHistoryRepository
import com.example.abc2019sconferenceapp.fragment.search.domain.entity.SearchHistoryEntity
import com.example.abc2019sconferenceapp.fragment.search.infra.SearchHistoryRepositoryImpl
import kotlinx.android.synthetic.main.fragment_search.view.*
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
    val binding = com.example.abc2019sconferenceapp.databinding.FragmentSearchBinding.inflate(
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

    GlobalScope.launch(Dispatchers.Main) {
      try {
        val searchHistory = withContext(Dispatchers.IO) { searchHistoryReadonlyRepository.loadSearchHistry() }.convert()
        val searchHistoryAdapter = SearchHistoryAdapter()
        searchHistoryAdapter.submitList(searchHistory)

        searchHistoryAdapter.searchHistoryItemClickListener = object : SearchHistoryItemClickListener {
          override fun onClick(searchHistoryBindingItem: SearchHistoryBindingItem) {
            if (searchHistoryBindingItem.queryText.isEmpty()) {
              return
            }
            binding.textView.searchBar.text.append(searchHistoryBindingItem.queryText)
          }
        }

        binding.searchHistoryRecyclerView.adapter = searchHistoryAdapter
        binding.searchHistoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
      } catch (e: Exception) {
        Toast.makeText(requireContext(), "検索履歴の取得に失敗しました", Toast.LENGTH_SHORT).show()
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
