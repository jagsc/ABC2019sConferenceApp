package jagsc.jp.abc2019Sconferenceapp.fragment.search.domain

import jagsc.jp.abc2019Sconferenceapp.fragment.search.domain.entity.SearchHistoryEntity

internal interface SearchHistoryReadonlyRepository {
  suspend fun loadSearchHistry(): List<SearchHistoryEntity>
}