package com.varunkumar.mymind.presentation.bookmark

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.varunkumar.mymind.ui.theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    id: Int? = null,
    onBackButtonClick: () -> Unit
) {
    val viewModel = hiltViewModel<BookmarkViewModel>()
    val context = LocalContext.current
    val bookmark by viewModel.bookmark.collectAsStateWithLifecycle()
    val imageToText by viewModel.imageToText.collectAsStateWithLifecycle()

    if (id != -1) id?.let { viewModel.getBookmark(id) }

    Scaffold(
        containerColor = Color.White,
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "New Bookmark",
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
        Column(
            modifier = modifier
                .padding(innerPadding)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(primaryColor)
                .padding(16.dp)
        ) {
            Text(
                text = "title",
                style = MaterialTheme.typography.bodySmall
            )
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = bookmark.title,
                textStyle = MaterialTheme.typography.titleLarge,
                onValueChange = viewModel::onBookmarkTitleChange
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "content",
                style = MaterialTheme.typography.bodySmall
            )
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = bookmark.snippetText,
                textStyle = MaterialTheme.typography.bodyMedium,
                onValueChange = viewModel::onBookmarkTextChange
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (bookmark.imageUri == null) {
                ImagePicker(
                    modifier = Modifier.fillMaxWidth(),
                    onImageSelected = { uri ->
                        viewModel.onBookmarkImageChange(uri.toString())
                    }
                )
            } else {
                Text(
                    text = "image",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(2.dp))
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .clickable {
                            viewModel.onBookmarkImageChange(null)
                        },
                    model = bookmark.imageUri,
                    contentDescription = "images"
                )

                viewModel.analyseImage(context)
                Spacer(modifier = Modifier.height(10.dp))

                imageToText?.let { text ->
                    Column (
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White)
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                modifier = Modifier.size(20.dp),
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(5.dp))

                            Text(
                                text = "Detected Text From Image.",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }


                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    onImageSelected: (Uri) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onImageSelected(uri)
        }
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(40.dp))
            .background(Color.White)
            .clickable {
                launcher.launch("image/*")
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Add Image",
            style = MaterialTheme.typography.bodyMedium
        )

        IconButton(
            onClick = { }
        ) {
            Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "Add Image")
        }
    }
}
