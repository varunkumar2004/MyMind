package com.varunkumar.mymind.presentation.bookmark

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.varunkumar.mymind.data.models.Bookmark
import com.varunkumar.mymind.presentation.HomeViewModel
import com.varunkumar.mymind.ui.theme.CustomTypography
import com.varunkumar.mymind.ui.theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    id: Int? = null,
    onBackButtonClick: () -> Unit
) {
    val viewModel = hiltViewModel<BookmarkViewModel>()
    val bookmark by viewModel.bookmark.collectAsStateWithLifecycle()
    if (id != -1) id?.let { viewModel.getBookmark(id) }

    Scaffold(
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

            bookmark.imageUri?.let { image ->
                Text(
                    text = "images",
                    style = MaterialTheme.typography.bodySmall
                )
                AsyncImage(
                    model = image,
                    contentDescription = "images"
                )
            }
        }
    }
}

@Composable
fun ImagePicker(modifier: Modifier = Modifier) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }
}
