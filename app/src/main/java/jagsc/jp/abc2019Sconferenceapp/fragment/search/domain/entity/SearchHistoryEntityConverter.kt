package jagsc.jp.abc2019Sconferenceapp.fragment.search.domain.entity

import jagsc.jp.abc2019Sconferenceapp.fragment.search.infra.SearchHistory

internal fun SearchHistory.convert(): SearchHistoryEntity = SearchHistoryEntity(
  this.id,
  this.queryName
)

internal fun List<SearchHistory>.convert(): List<SearchHistoryEntity> = this.map { it.convert() }