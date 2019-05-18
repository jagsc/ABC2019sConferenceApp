package jagsc.jp.abc2019Sconferenceapp.fragment.search.infra

import android.content.Context
import jagsc.jp.abc2019Sconferenceapp.fragment.search.domain.SearchHistoryReadonlyRepository
import jagsc.jp.abc2019Sconferenceapp.fragment.search.domain.SearchHistoryRepository
import jagsc.jp.abc2019Sconferenceapp.fragment.search.domain.entity.SearchHistoryEntity
import jagsc.jp.abc2019Sconferenceapp.fragment.search.domain.entity.convert

internal class SearchHistoryRepositoryImpl(
  private val context: Context
) : SearchHistoryRepository, SearchHistoryReadonlyRepository {
  override suspend fun addSearchHistory(searchHistoryEntity: SearchHistoryEntity) {
    val database = SearchHistoryDatabase.getDatabase(context) ?: throw Exception()
    val dao = database.searchHistoryDao()
    dao.insertSearchHistory(
      searchHistoryEntity.convert()
    )
  }

  override suspend fun loadSearchHistry(): List<SearchHistoryEntity> {
    val database = SearchHistoryDatabase.getDatabase(context) ?: throw Exception()
    val dao = database.searchHistoryDao()
    return dao.getAllHistory().convert()
  }

  override suspend fun clearSearchHistory() {
    val database = SearchHistoryDatabase.getDatabase(context) ?: throw Exception()
    val dao = database.searchHistoryDao()
    dao.deleteAll()
  }
}