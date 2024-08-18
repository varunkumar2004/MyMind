package com.varunkumar.mymind.presentation.share

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.varunkumar.mymind.R
import com.varunkumar.mymind.data.BookmarkRepository
import com.varunkumar.mymind.data.models.Bookmark
import com.varunkumar.mymind.ui.theme.MyMindTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ShareReceiverActivity : ComponentActivity() {

    @Inject
    lateinit var bookmarkRepository: BookmarkRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_receiver)

        val sharedText = handleIncomingIntent(intent)

        setContent {
            MyMindTheme {
                ShareReceiverScreen(
                    modifier = Modifier.fillMaxSize(),
                    sharedText = sharedText,
                    onSave = { saveText(sharedText) },
                    onCancel = { finish() }
                )
            }
        }
    }

    private fun handleIncomingIntent(intent: Intent): String? {
        return if (intent.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            intent.getStringExtra(Intent.EXTRA_TEXT)
        } else {
            null
        }
    }

    private fun saveText(text: String?) {
        lifecycleScope.launch {
            text?.let {
                val bookmark = Bookmark(
                    title = System.currentTimeMillis().toString(),
                    snippetText = it
                )
                bookmarkRepository.insert(bookmark)
                finish()
            }
        }
    }
}

