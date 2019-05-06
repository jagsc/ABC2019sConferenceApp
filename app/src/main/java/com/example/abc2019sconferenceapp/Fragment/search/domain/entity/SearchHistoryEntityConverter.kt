package com.example.abc2019sconferenceapp.fragment.search.domain.entity

import com.example.abc2019sconferenceapp.fragment.search.infra.SearchHistory

internal fun SearchHistory.convert(): SearchHistoryEntity = SearchHistoryEntity(
  this.id,
  this.queryName
)

internal fun List<SearchHistory>.convert(): List<SearchHistoryEntity> = this.map { it.convert() }