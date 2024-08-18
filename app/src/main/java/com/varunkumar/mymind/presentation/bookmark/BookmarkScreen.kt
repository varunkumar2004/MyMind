package com.varunkumar.mymind.presentation.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.varunkumar.mymind.data.models.Bookmark
import com.varunkumar.mymind.presentation.BookmarksViewModel
import com.varunkumar.mymind.ui.theme.CustomTypography
import com.varunkumar.mymind.ui.theme.primaryColor
import com.varunkumar.mymind.ui.theme.secondaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarksViewModel,
    bookmark: Bookmark,
    onBackButtonClick: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Home",
                        style = CustomTypography.bodyLarge
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
                                viewModel.delete(bookmark)
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
                                viewModel.delete(bookmark)
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
//            bookmark.imageUri?.let { image ->
//                AsyncImage(
//                    model = image,
//                    contentDescription = null
//                )
//            }
            ListItem(
                colors = ListItemDefaults.colors(
                    containerColor = Color.Transparent
                ),
                headlineContent = {
                    BasicTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = bookmark.title,
                        textStyle = CustomTypography.bodyLarge,
                        onValueChange = viewModel::onSearchQueryChange
                    )
                },
                leadingContent = {
                    Text("Title")
                }
            )

            bookmark.imageUri?.let { image ->
                ListItem(
                    colors = ListItemDefaults.colors(
                        containerColor = Color.Transparent
                    ),
                    headlineContent = {
                        AsyncImage(
                            model = image,
                            contentDescription = "images"
                        )
                    },
                    leadingContent = {
                        Text("Images")
                    }
                )
            }

            ListItem(
                colors = ListItemDefaults.colors(
                    containerColor = Color.Transparent
                ),
                headlineContent = {
                    BasicTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = bookmark.snippetText,
                        textStyle = CustomTypography.bodyMedium,
                        onValueChange = viewModel::onSearchQueryChange
                    )
                },
                leadingContent = {
                    Text("content")
                }
            )
        }
    }
}