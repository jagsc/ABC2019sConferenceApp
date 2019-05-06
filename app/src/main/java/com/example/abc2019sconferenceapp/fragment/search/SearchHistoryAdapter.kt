package com.example.abc2019sconferenceapp.fragment.search

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback

// androidx に置き換えたら ListAdapter に置き換える
internal class SearchHistoryAdapter : androidx.recyclerview.widget.ListAdapter<SearchHistoryBindingItem, SearchHistoryViewHolder>(ITEM_CALLBACK) {


  companion object {
    val ITEM_CALLBACK = object : ItemCallback<SearchHistoryBindingItem>() {
      override fun areItemsTheSame(oldItem: SearchHistoryBindingItem, newItem: SearchHistoryBindingItem): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(oldItem: SearchHistoryBindingItem, newItem: SearchHistoryBindingItem): Boolean {
        return oldItem == newItem
      }
    }
  }

  var searchHistoryItemClickListener: SearchHistoryItemClickListener? = null

  override fun onCreateViewHolder(
    container: ViewGroup,
    position: Int
  ): SearchHistoryViewHolder {
    return SearchHistoryViewHolder.create(container)
  }


  override fun onBindViewHolder(
    viewHolder: SearchHistoryViewHolder,
    position: Int
  ) {
    viewHolder.bindTo(
      getItem(position),
      searchHistoryItemClickListener)
  }
}
