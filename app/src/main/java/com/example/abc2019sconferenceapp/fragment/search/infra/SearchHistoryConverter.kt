package com.example.abc2019sconferenceapp.fragment.search.infra

import com.example.abc2019sconferenceapp.fragment.search.domain.entity.SearchHistoryEntity

internal fun SearchHistoryEntity.convert(): SearchHistory = SearchHistory(
  this.id,
  this.queryName
)