package com.varunkumar.mymind.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class Bookmark(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String = "",
    val snippetText: String = "",
    val imageUri: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)
