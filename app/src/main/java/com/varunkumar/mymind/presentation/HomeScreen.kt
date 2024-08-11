package com.varunkumar.mymind.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.varunkumar.mymind.data.models.Bookmark
import com.varunkumar.mymind.presentation.components.BookmarkView
import com.varunkumar.mymind.presentation.components.CustomTopAppBar
import com.varunkumar.mymind.ui.theme.radialGradient

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarksViewModel,
    onSearchAction: () -> Unit
) {
    val bookmarks by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "My Mind",
                onActionClick = onSearchAction
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 0.dp
                ),
                shape = CircleShape,
                containerColor = Color.Black,
                contentColor = Color.White,
                onClick = {
                    viewModel.insert(
                        Bookmark(
                            title = "New Bookmark",
                            snippetText = "New Description"
                        )
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .background(radialGradient),
        ) {
            LazyColumn(
                modifier = Modifier
//                    .padding(horizontal = 16.dp),
            ) {
                val radius = RoundedCornerShape(40.dp)

                items(bookmarks) { bookmark ->
//                    ListItem(
//                        colors = ListItemDefaults.colors(
//                            containerColor = Color.Transparent
//                        ),
//                        headlineContent = {
//                            Text(
//                                text = bookmark.title,
//                                style = CustomTypography.bodyLarge
//                            )
//                        },
//                        supportingContent = {
//                            bookmark.snippetText?.let {snippet ->
//                                Text(
//                                    text = snippet,
//                                    style = CustomTypography.bodyMedium
//                                )
//                            }
//                        }
//                    )

//                    Spacer(modifier = Modifier.height(5.dp))
                    BookmarkView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        bookmark = bookmark
                    )

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Color.LightGray,
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}