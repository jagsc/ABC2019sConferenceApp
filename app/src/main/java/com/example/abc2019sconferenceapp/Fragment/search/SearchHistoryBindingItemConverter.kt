package com.example.abc2019sconferenceapp.fragment.search

import com.example.abc2019sconferenceapp.fragment.search.domain.entity.SearchHistoryEntity

internal fun SearchHistoryEntity.convert(): SearchHistoryBindingItem =
  SearchHistoryBindingItem(
    this.id,
    this.queryName
  )

internal fun List<SearchHistoryEntity>.convert(): List<SearchHistoryBindingItem> = this.map { it.convert() }