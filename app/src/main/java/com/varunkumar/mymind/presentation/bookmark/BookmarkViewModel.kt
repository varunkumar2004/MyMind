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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    private val textRecognizer: TextRecognizer
) : ViewModel() {
    private val _bookmark = MutableStateFlow(Bookmark())
    val bookmark = _bookmark.asStateFlow()

    var extractedText = MutableStateFlow("")
        private set

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

    fun analyseImage(context: Context) {
        val inputImage: InputImage

        try {
            inputImage = InputImage.fromBitmap(
                loadBitmapFromUri(context = context, uri = Uri.parse(_bookmark.value.imageUri)),
                0
            )

            textRecognizer.process(inputImage)
                .addOnSuccessListener { visionText ->
                    extractedText.update { visionText.toString() }
                }.addOnFailureListener {
                    Log.d("error with model", it.message.toString())
                }
        } catch (e: Exception) {
            e.printStackTrace()
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