package jagsc.jp.abc2019Sconferenceapp.fragment.search

import android.view.LayoutInflater
import android.view.ViewGroup
import jagsc.jp.abc2019Sconferenceapp.databinding.HistoryItemBinding

internal class SearchHistoryViewHolder private constructor(
  private val binding: jagsc.jp.abc2019Sconferenceapp.databinding.HistoryItemBinding
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