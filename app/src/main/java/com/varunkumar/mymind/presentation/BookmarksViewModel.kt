package com.varunkumar.mymind.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varunkumar.mymind.data.BookmarkRepository
import com.varunkumar.mymind.data.models.Bookmark
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {
    private val _state = MutableStateFlow(BookmarksState())
    private val _bookmarks =
        bookmarkRepository.allBookmarks.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    val state = _bookmarks

    fun insert(bookmark: Bookmark) {
        viewModelScope.launch {
            Log.d("new bookmark", bookmark.toString())
            bookmarkRepository.insert(bookmark)
        }
    }

    fun delete(bookmark: Bookmark) {
        viewModelScope.launch {
            bookmarkRepository.delete(bookmark)
        }
    }
}