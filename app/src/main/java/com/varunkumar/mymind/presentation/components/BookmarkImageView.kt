package com.varunkumar.mymind.presentation.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.varunkumar.mymind.data.models.Bookmark
import com.varunkumar.mymind.presentation.bookmark.BookmarkViewModel
import com.varunkumar.mymind.ui.theme.secondaryColor

@Composable
fun BookmarkImageView(
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel,
    shape: RoundedCornerShape = RoundedCornerShape(20.dp),
    bookmark: Bookmark,
    onImageViewClick: (List<String>?) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uris ->
        viewModel.onBookmarkImageChange(
            uris.map { uri ->
                uri.toString()
            }
        )
    }

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Black,
        contentColor = Color.White
    )

    if (bookmark.images != null) {
        ListItem(
            modifier = Modifier
                .clip(shape)
                .clickable {
                    launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                },
            colors = ListItemDefaults.colors(
                containerColor = Color.White
            ),
            leadingContent = {
                Text(
                    text = "images",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            headlineContent = {
                Box {
                    bookmark.images.forEachIndexed { index, image ->
                        if (index <= 3) {
                            AsyncImage(
                                modifier = Modifier
                                    .width(100.dp)
                                    .aspectRatio(1 / 1.5f)
                                    .offset(x = if (index == 0) 0.dp else (index * 100 - index * 40).dp)
                                    .scale(if (index == 0) 1f else (1 - 0.1 * index).toFloat())
                                    .zIndex((100 - index).toFloat())
                                    .clip(RoundedCornerShape(20.dp)),
                                model = image,
                                contentDescription = null
                            )
                        }
                    }
                }
            },
            trailingContent = {
                if (bookmark.images.size > 3) {
                    Box(
                        modifier = Modifier
                            .zIndex(10000f)
                            .clip(CircleShape)
                            .background(secondaryColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "+${bookmark.images.size - 4}", color = Color.Black
                        )
                    }
                }
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = modifier,
                colors = buttonColors,
                onClick = { onImageViewClick(bookmark.images) }
            ) {
                Text(text = "View")
            }

            Spacer(Modifier.width(5.dp))

            Button(
                modifier = modifier,
                colors = buttonColors,
                onClick = {

                }
            ) {
                Text(text = "Image text")
            }
        }
    } else {
        Button(
            modifier = modifier,
            colors = buttonColors,
            onClick = {
                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        ) {
            Text(text = "Pick image from gallery")
        }
    }
}