package com.varunkumar.mymind.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varunkumar.mymind.data.BookmarkRepository
import com.varunkumar.mymind.data.models.Bookmark
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    private val _bookmarks = bookmarkRepository.allBookmarks

    var searchQuery = MutableStateFlow("")
        private set

    val filterBookmarks = searchQuery.map { query ->
        _state.value.bookmarks.filter {
            it.title.contains(query, ignoreCase = true) || it.content.contains(query, ignoreCase = true)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _bookmarks) { state, bookmarks ->
        _state.update { it.copy(bookmarks = bookmarks) }
        state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), HomeState())

    fun onSearchQueryChange(query: String)  {
        searchQuery.update { query }
    }

    fun getBookmarkById(id: Int) {
        viewModelScope.launch {
            _state.update { it.copy(bookmark = bookmarkRepository.getBookmarkById(id)) }
        }
    }

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