package com.example.abc2019sconferenceapp.fragment.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.abc2019sconferenceapp.databinding.HistoryItemBinding

internal class SearchHistoryViewHolder private constructor(
  private val binding: HistoryItemBinding
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
  companion object {
    fun create(
      container: ViewGroup
    ): SearchHistoryViewHolder {
      return SearchHistoryViewHolder(
        HistoryItemBinding.inflate(
          LayoutInflater.from(container.context),
          container,
          false
        )
      )
    }
  }

  fun bindTo(
    searchHistoryBindingItem: SearchHistoryBindingItem,
    searchHistoryItemClickListener: SearchHistoryItemClickListener?
  ) {
    binding.bindingitem = searchHistoryBindingItem
    binding.root.setOnClickListener { searchHistoryItemClickListener?.onClick(searchHistoryBindingItem) }
  }
}