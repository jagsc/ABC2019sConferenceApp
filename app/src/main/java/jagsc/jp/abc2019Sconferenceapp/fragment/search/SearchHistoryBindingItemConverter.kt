package jagsc.jp.abc2019Sconferenceapp.fragment.search

import jagsc.jp.abc2019Sconferenceapp.fragment.search.domain.entity.SearchHistoryEntity

internal fun SearchHistoryEntity.convert(): SearchHistoryBindingItem =
  SearchHistoryBindingItem(
    this.id,
    this.queryName
  )

internal fun List<SearchHistoryEntity>.convert(): List<SearchHistoryBindingItem> = this.map { it.convert() }