package com.varunkumar.mymind.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.varunkumar.mymind.data.models.Bookmark
import com.varunkumar.mymind.ui.theme.CustomTypography

@Composable
fun BookmarkView(
    modifier: Modifier = Modifier,
    bookmark: Bookmark
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = bookmark.title,
            style = CustomTypography.bodyLarge
        )

        bookmark.snippetText?.let { Text(text = bookmark.snippetText) }
    }
}