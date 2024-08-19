package com.varunkumar.mymind.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.varunkumar.mymind.presentation.HomeViewModel
import com.varunkumar.mymind.presentation.components.FloatingBottomBar
import com.varunkumar.mymind.ui.theme.CustomTypography
import com.varunkumar.mymind.ui.theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onAddBookmarkButtonClick: () -> Unit,
    onSearchBookmarkButtonClick: () -> Unit,
    onBookmarkAction: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = {
                    Text(
                        text = "My Mind",
                        style = CustomTypography.titleLarge
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingBottomBar(
                onAddButtonClick = onAddBookmarkButtonClick,
                onSearchButtonClick = onSearchBookmarkButtonClick
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            LazyColumn (
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(state.bookmarks) { bookmark ->
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(30.dp))
                            .clickable {
                                onBookmarkAction(bookmark.id)
                            },
                        colors = ListItemDefaults.colors(
                            containerColor = primaryColor
                        ),
                        headlineContent = {
                            Text(
                                text = bookmark.title,
                                style = MaterialTheme.typography.bodyLarge,
                                maxLines = 1
                            )
                        },
                        supportingContent = {
                            Text(
                                text = bookmark.content.take(130) + "...",
                                style = MaterialTheme.typography.bodySmall,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        trailingContent = {
                            if (bookmark.images != null) {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(20.dp)),
                                    model = bookmark.images,
                                    contentDescription = null
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomePrev() {
//    HomeScreen() { }
}