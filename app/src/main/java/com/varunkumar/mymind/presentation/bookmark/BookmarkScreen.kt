package com.varunkumar.mymind.presentation.bookmark

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.varunkumar.mymind.presentation.components.BookmarkImageView
import com.varunkumar.mymind.ui.theme.primaryColor
import com.varunkumar.mymind.ui.theme.secondaryColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    id: Int,
    onImagesSelected: (List<String>?) -> Unit,
    onBackButtonClick: () -> Unit
) {
    val viewModel = hiltViewModel<BookmarkViewModel>()
    if (id != -1) viewModel.getBookmark(id)
    val bookmark by viewModel.bookmark.collectAsStateWithLifecycle()
    var isBottomSheetOpen by remember { mutableStateOf(false) }
    val imageToText by viewModel.imageToText.collectAsStateWithLifecycle()

    if (isBottomSheetOpen&& imageToText != null) {
        ModalBottomSheet(
            onDismissRequest = { isBottomSheetOpen = false },
            containerColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Text extracted from image",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = imageToText ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

    Scaffold(
        containerColor = Color.White,
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Home",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                viewModel.upsertBookmark()
                                onBackButtonClick()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Done,
                                contentDescription = null
                            )
                        }

                        IconButton(
                            onClick = {
                                viewModel.deleteBookmark()
                                onBackButtonClick()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        val shape = RoundedCornerShape(30.dp)

        Column(
            modifier = modifier
                .padding(innerPadding)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(primaryColor)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = shape,
                value = bookmark.title,
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = viewModel::onBookmarkTitleChange,
                label = {
                    Text(text = "title")
                },
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp),
                shape = shape,
                value = bookmark.content,
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = viewModel::onBookmarkTextChange,
                label = {
                    Text(text = "content")
                }
            )

            BookmarkImageView(
                modifier = Modifier
                    .height(TextFieldDefaults.MinHeight),
                viewModel = viewModel,
                shape = shape,
                bookmark = bookmark,
                onImageViewClick = onImagesSelected
            )
        }
    }
}
