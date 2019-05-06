package com.example.abc2019sconferenceapp.fragment.search.infra

import android.content.Context
import com.example.abc2019sconferenceapp.fragment.search.domain.SearchHistoryReadonlyRepository
import com.example.abc2019sconferenceapp.fragment.search.domain.SearchHistoryRepository
import com.example.abc2019sconferenceapp.fragment.search.domain.entity.SearchHistoryEntity
import com.example.abc2019sconferenceapp.fragment.search.domain.entity.convert

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