package com.varunkumar.mymind.presentation

import com.varunkumar.mymind.data.models.Bookmark

data class BookmarksState (
    val bookmarks: List<Bookmark> = emptyList(),
    val searchQuery: String = "",

    val bookmark: Bookmark? = null
)
