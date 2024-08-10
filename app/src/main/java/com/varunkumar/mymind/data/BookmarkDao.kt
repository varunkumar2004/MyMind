package com.varunkumar.mymind.data

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.varunkumar.mymind.data.models.Bookmark
import kotlinx.coroutines.flow.Flow

interface BookmarkDao {
    @Query("SELECT * FROM bookmarks")
    fun getAllBookmarks(): Flow<List<Bookmark>>

    @Upsert
    suspend fun insertBookmark(bookmark: Bookmark)

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)
}