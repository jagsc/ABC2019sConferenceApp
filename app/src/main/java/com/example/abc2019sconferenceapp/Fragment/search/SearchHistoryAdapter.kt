package com.example.abc2019sconferenceapp.fragment.search

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

// androidx に置き換えたら ListAdapter に置き換える
internal class SearchHistoryAdapter(
  private val histryList: List<SearchHistoryBindingItem>
) : RecyclerView.Adapter<SearchHistoryViewHolder>() {

  var searchHistoryItemClickListener: SearchHistoryItemClickListener? = null

  override fun onCreateViewHolder(
    container: ViewGroup,
    position: Int
  ): SearchHistoryViewHolder {
    return SearchHistoryViewHolder.create(container)
  }

  override fun getItemCount(): Int {
    return histryList.count()
  }

  override fun onBindViewHolder(
    viewHolder: SearchHistoryViewHolder,
    position: Int
  ) {
    viewHolder.bindTo(
      histryList.elementAt(position),
      searchHistoryItemClickListener)
  }
}
