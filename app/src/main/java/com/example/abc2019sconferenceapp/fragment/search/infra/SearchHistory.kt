package com.example.abc2019sconferenceapp.fragment.search.infra

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "search_history")
internal data class SearchHistory(
  @PrimaryKey
  @NotNull
  val id: Int = 0,

  @ColumnInfo(name = "query_name")
  val queryName: String = ""
)