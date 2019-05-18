package jagsc.jp.abc2019Sconferenceapp.fragment.search.domain

import jagsc.jp.abc2019Sconferenceapp.fragment.search.domain.entity.SearchHistoryEntity

internal interface SearchHistoryRepository {
  suspend fun addSearchHistory(searchHistoryEntity: SearchHistoryEntity)
  suspend fun clearSearchHistory()
}