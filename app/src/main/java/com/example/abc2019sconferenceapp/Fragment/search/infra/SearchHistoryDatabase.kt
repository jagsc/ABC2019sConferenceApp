package com.example.abc2019sconferenceapp.fragment.search.infra

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SearchHistoryDao::class], version = 1)
internal abstract class SearchHistoryDatabase : RoomDatabase() {

  abstract fun searchHistoryDao(): SearchHistoryDao

  companion object {
    @JvmStatic
    var INSTANCE: SearchHistoryDatabase? = null

    fun getDatabase(context: Context): SearchHistoryDatabase? {
      if (INSTANCE == null) {
        synchronized(SearchHistoryDatabase::class.java) {
          if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.applicationContext, SearchHistoryDatabase::class.java, "search_history").build()
          }
        }
      }
      return INSTANCE
    }
  }
}