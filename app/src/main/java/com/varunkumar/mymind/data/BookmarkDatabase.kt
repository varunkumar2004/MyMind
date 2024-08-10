package com.varunkumar.mymind.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.varunkumar.mymind.data.models.Bookmark

@Database(entities = [Bookmark::class], version = 1)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun userDao(): BookmarkDao
}