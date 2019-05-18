package jagsc.jp.abc2019Sconferenceapp.fragment.search.infra

import jagsc.jp.abc2019Sconferenceapp.fragment.search.domain.entity.SearchHistoryEntity

internal fun SearchHistoryEntity.convert(): SearchHistory = SearchHistory(
  this.id,
  this.queryName
)