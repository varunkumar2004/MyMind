package com.varunkumar.mymind.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.varunkumar.mymind.ui.theme.radialGradient

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarksViewModel,
    onSearchAction: () -> Unit
) {
    val bookmarks by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "My Mind",
                onActionClick = onSearchAction
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .background(radialGradient),
        ) {

        }
    }
}