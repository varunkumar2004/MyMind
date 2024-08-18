package com.varunkumar.mymind.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.varunkumar.mymind.data.models.Bookmark
import com.varunkumar.mymind.presentation.BookmarksViewModel
import com.varunkumar.mymind.presentation.components.BookmarkView
import com.varunkumar.mymind.presentation.components.CustomTopAppBar
import com.varunkumar.mymind.presentation.components.FloatingBottomBar
import com.varunkumar.mymind.ui.theme.CustomTypography
import com.varunkumar.mymind.ui.theme.radialGradient
import com.varunkumar.mymind.ui.theme.secondaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarksViewModel,
    onAddBookmarkButtonClick: () -> Unit,
    onBookmarkAction: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = secondaryColor
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
                viewModel = viewModel,
                onAddButtonClick = onAddBookmarkButtonClick
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 10.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )

            LazyVerticalStaggeredGrid(
                modifier = Modifier.fillMaxWidth(),
                columns = StaggeredGridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalItemSpacing = 5.dp
            ) {
                items(state.bookmarks) { bookmark ->
                    BookmarkView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(20.dp))
                            .background(secondaryColor)
                            .clickable {
                                onBookmarkAction(bookmark.id)
                            }
                            .padding(16.dp),
                        bookmark = bookmark
                    )
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