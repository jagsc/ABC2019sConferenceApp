package com.example.abc2019sconferenceapp.fragment.search.domain

import com.example.abc2019sconferenceapp.fragment.search.domain.entity.SearchHistoryEntity

internal interface SearchHistoryRepository {
  suspend fun addSearchHistory(searchHistoryEntity: SearchHistoryEntity)
}