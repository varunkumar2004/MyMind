package com.varunkumar.mymind.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.varunkumar.mymind.data.models.Bookmark
import com.varunkumar.mymind.ui.theme.CustomTypography

@Composable
fun BookmarkView(
    modifier: Modifier = Modifier,
    bookmark: Bookmark
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = bookmark.title,
            style = CustomTypography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Clip
        )

        if (bookmark.imageUri != null) {
            AsyncImage(
                model = bookmark.imageUri,
                contentDescription = "bookmark image"
            )
        } else {
            Text(
                style = CustomTypography.bodySmall,
                text = bookmark.snippetText,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}