package com.varunkumar.mymind.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varunkumar.mymind.data.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
): ViewModel() {
    private val _bookmarks = bookmarkRepository.allBookmarks
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    val state = combine(_bookmarks, _query) {
        bookmarks, query ->
        bookmarks.filter {
            it.title.contains(query) || it.content.contains(query)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    fun onQueryChange(text: String) {
        _query.update { text }
    }
}