package com.varunkumar.mymind.presentation.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    images: List<String>,
    onBackButtonClick: () -> Unit
) {
    var imageViewIndex by remember { mutableIntStateOf(0) }

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
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White
                ),
                enabled = (imageViewIndex != 0),
                onClick = {
                    if (imageViewIndex > 0) {
                        imageViewIndex--
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, null)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp)
            ) {
                ImageView(
                    modifier = modifier,
                    url = images[imageViewIndex]
                )
            }

            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White
                ),
                enabled = (imageViewIndex != images.size - 1),
                onClick = {
                    if (imageViewIndex < images.size - 1) {
                        imageViewIndex++
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, null)
            }
        }
    }
}

@Composable
private fun ImageView(
    modifier: Modifier = Modifier,
    url: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = url,
            contentDescription = null
        )
    }
}