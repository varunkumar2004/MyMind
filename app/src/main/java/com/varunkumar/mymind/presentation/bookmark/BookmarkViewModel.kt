package com.varunkumar.mymind.presentation.bookmark

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognizer
import com.varunkumar.mymind.data.BookmarkRepository
import com.varunkumar.mymind.data.models.Bookmark
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    private val textRecognizer: TextRecognizer,
    @ApplicationContext context: Context
) : ViewModel() {
    private val _bookmark = MutableStateFlow(Bookmark())
    val bookmark = _bookmark.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val imageToText = _bookmark.flatMapLatest { bookmark ->
        bookmark.imageUri?.let {
            analyseImage(context)
        } ?: flow { emit(null) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

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

    fun onBookmarkImageChange(image: String?) {
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

    fun analyseImage(context: Context): Flow<String?> = flow {
        try {
            val inputImage = InputImage.fromBitmap(
                loadBitmapFromUri(context = context, uri = Uri.parse(_bookmark.value.imageUri)),
                0
            )
            val textResult = textRecognizer.process(inputImage).await()
            emit(textResult.text)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(null)
        }
    }
}

private fun loadBitmapFromUri(context: Context, uri: Uri): Bitmap {
    return if (Build.VERSION.SDK_INT < 28) {
        @Suppress("DEPRECATION")
        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    } else {
        val source = ImageDecoder.createSource(context.contentResolver, uri)
        ImageDecoder.decodeBitmap(source)
    }
}