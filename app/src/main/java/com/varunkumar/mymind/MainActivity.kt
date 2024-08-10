package com.varunkumar.mymind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.varunkumar.mymind.presentation.BookmarksViewModel
import com.varunkumar.mymind.presentation.HomeScreen
import com.varunkumar.mymind.ui.theme.MyMindTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val sModifier = Modifier.fillMaxSize()
            val bookmarksViewModel = hiltViewModel<BookmarksViewModel>()

            MyMindTheme {
                HomeScreen(
                    modifier = sModifier,
                    viewModel = bookmarksViewModel,
                    onSearchAction = {

                    }
                )
            }
        }
    }
}