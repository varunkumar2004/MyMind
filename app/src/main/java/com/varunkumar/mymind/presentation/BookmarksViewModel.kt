package com.varunkumar.mymind.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
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
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {
    private val _state = MutableStateFlow(BookmarksState())
    private val _bookmarks = bookmarkRepository.allBookmarks.map {bookmarks ->
        _state.update { it.copy(bookmarks = bookmarks) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList<Bookmark>())

    val state = _state.asStateFlow()
}