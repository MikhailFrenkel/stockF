package com.frenkel.database_android.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.frenkel.database_android.models.NewsRoomDbo

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    suspend fun getNews(): List<NewsRoomDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: List<NewsRoomDbo>)
}