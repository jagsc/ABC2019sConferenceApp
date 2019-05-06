package com.example.abc2019sconferenceapp.fragment.search.infra

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
internal interface SearchHistoryDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertSearchHistory(searchHistory: SearchHistory)

  @Query("select * from search_history")
  fun getAllHistory(): List<SearchHistory>

  @Query("delete from search_history")
  fun deleteAll(): Unit
}