package com.varunkumar.mymind.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varunkumar.mymind.data.BookmarkRepository
import com.varunkumar.mymind.data.models.Bookmark
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {
    private val _bookmark = MutableStateFlow(Bookmark())
    val bookmark = _bookmark.asStateFlow()

    fun getBookmark(id: Int = -1) {
        viewModelScope.launch {
            val bookmark = bookmarkRepository.getBookmarkById(id)
            _bookmark.update { bookmark ?: Bookmark() }
        }
    }

    fun onBookmarkTitleChange(title: String) {
        _bookmark.update { it.copy(title = title) }
    }

    fun onBookmarkTextChange(text: String) {
        _bookmark.update { it.copy(snippetText = text) }
    }

    fun onBookmarkImageChange(image: String) {
        _bookmark.update { it.copy(imageUri = image) }
    }

    fun deleteBookmark() {
        viewModelScope.launch {
            bookmarkRepository.delete(_bookmark.value)
        }
    }

    fun upsertBookmark() {
        viewModelScope.launch {
            bookmarkRepository.insert(_bookmark.value)
        }
    }
}