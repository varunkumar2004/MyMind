package com.varunkumar.mymind.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.varunkumar.mymind.data.models.Bookmark
import com.varunkumar.mymind.data.models.Converters

@Database(entities = [Bookmark::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}