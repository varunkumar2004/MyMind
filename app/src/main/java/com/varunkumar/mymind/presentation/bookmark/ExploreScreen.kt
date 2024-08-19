package com.varunkumar.mymind.presentation.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit
) {
    val images = listOf(
        "https://picsum.photos/200/300",
        "https://picsum.photos/200/300",
        "https://picsum.photos/200/300",
        "https://picsum.photos/200/300",
    )

    val imageViewIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier,
        containerColor = Color.Black,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Images")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(imageVector = Icons.Default.Close, null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                onClick = {}
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, null)
            }

            LazyRow {
                items(images) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth(),
                        model = it,
                        contentDescription = null
                    )
                }
            }


            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                onClick = {}
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, null)
            }
        }
    }
}