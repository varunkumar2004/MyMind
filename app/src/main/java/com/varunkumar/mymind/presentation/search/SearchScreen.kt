package com.varunkumar.mymind.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.varunkumar.mymind.ui.theme.primaryColor
import com.varunkumar.mymind.ui.theme.secondaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onBookmarkAction: (Int) -> Unit,
    onBackButtonClick: () -> Unit
) {
    val viewModel = hiltViewModel<SearchViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()
    var activeSearchBar by remember { mutableStateOf(false) }

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
                        text = "Search",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackButtonClick
                    ) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
//            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = query,
                onValueChange = viewModel::onQueryChange,
                shape = RoundedCornerShape(40.dp),
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(
                            onClick = { viewModel.onQueryChange("") }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = null
                            )
                        }
                    }
                },
                colors = TextFieldDefaults.colors(
                    cursorColor = Color.Black,
                    focusedContainerColor = secondaryColor,
                    unfocusedContainerColor = secondaryColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "Search",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {

                item { Spacer(Modifier.height(10.dp)) }

                items(state) { bookmark ->
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
                                text = bookmark.snippetText.take(130) + "...",
                                style = MaterialTheme.typography.bodySmall,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        trailingContent = {
                            if (bookmark.imageUri != null) {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(20.dp)),
                                    model = bookmark.imageUri,
                                    contentDescription = null
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(2.dp))
                }
            }

//            LazyColumn(
//                modifier = Modifier.fillMaxWidth(),
//            ) {
//                items(state) { bookmark ->
//                    ListItem(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clip(shape = RoundedCornerShape(30.dp))
//                            .clickable {
//                                onBookmarkAction(bookmark.id)
//                            },
//                        colors = ListItemDefaults.colors(
//                            containerColor = primaryColor
//                        ),
//                        headlineContent = {
//                            Text(
//                                text = bookmark.title,
//                                style = MaterialTheme.typography.bodyLarge,
//                                maxLines = 1
//                            )
//                        },
//                        supportingContent = {
//                            Text(
//                                text = bookmark.snippetText.take(130) + "...",
//                                style = MaterialTheme.typography.bodySmall,
//                                overflow = TextOverflow.Ellipsis
//                            )
//                        },
//                        trailingContent = {
//                            if (bookmark.imageUri != null) {
//                                AsyncImage(
//                                    modifier = Modifier
//                                        .size(100.dp)
//                                        .clip(RoundedCornerShape(20.dp)),
//                                    model = bookmark.imageUri,
//                                    contentDescription = null
//                                )
//                            }
//                        }
//                    )
//                }
//            }
        }
    }
}

@Preview
@Composable
private fun SearchPrev() {
//    SearchScreen {
//
//    }
}