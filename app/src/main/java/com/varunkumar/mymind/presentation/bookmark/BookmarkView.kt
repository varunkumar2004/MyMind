package com.varunkumar.mymind.presentation.bookmark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.varunkumar.mymind.data.models.Bookmark

@Composable
fun BookmarkView(
    modifier: Modifier = Modifier,
    bookmark: Bookmark
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = bookmark.title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Clip
        )

        if (bookmark.imageUri != null) {
            AsyncImage(
                model = bookmark.imageUri,
                contentDescription = "bookmark image"
            )
        } else {
            if (bookmark.snippetText.isNotBlank()) {
                Text(
                    style = MaterialTheme.typography.bodySmall,
                    text = bookmark.snippetText,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}