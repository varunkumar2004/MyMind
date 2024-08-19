package com.varunkumar.mymind.presentation.bookmark

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.varunkumar.mymind.ui.theme.primaryColor
import com.varunkumar.mymind.ui.theme.secondaryColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    id: Int,
    onImagesSelected: (List<String>) -> Unit,
    onBackButtonClick: () -> Unit
) {
    val viewModel = hiltViewModel<BookmarkViewModel>()
    val context = LocalContext.current
    val bookmark by viewModel.bookmark.collectAsStateWithLifecycle()
    val imageToText by viewModel.imageToText.collectAsStateWithLifecycle()

    val images = listOf(
        "https://picsum.photos/200/300",
        "https://picsum.photos/200/300",
        "https://picsum.photos/200/300",
        "https://picsum.photos/200/300",
    )

    if (id != -1) viewModel.getBookmark(id)

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
                value = bookmark.snippetText,
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

            if (bookmark.imageUri != null) {
                ImagePicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(TextFieldDefaults.MinHeight),
                    onImageSelected = { uri ->
                        viewModel.onBookmarkImageChange(uri.toString())
                    }
                )
            } else {
//                AsyncImage(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clip(RoundedCornerShape(20.dp))
//                        .clickable {
//                            viewModel.onBookmarkImageChange(null)
//                        },
//                    model = bookmark.imageUri,
//                    contentDescription = "images"
//                )

                ListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape)
                        .clickable {
                            onImagesSelected(images)
                        },
                    leadingContent = {
                        Text(
                            text = "images",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    headlineContent = {
                        Box {
                            images.forEachIndexed { index, image ->
                                if (index <= 2) {
                                    AsyncImage(
                                        modifier = Modifier
                                            .width(100.dp)
                                            .aspectRatio(1 / 1.5f)
                                            .offset(x = if (index == 0) 0.dp else (index * 100 - index * 40).dp)
                                            .scale(if (index == 0) 1f else (1 - 0.1 * index).toFloat())
                                            .zIndex((100 - index).toFloat())
                                            .clip(RoundedCornerShape(20.dp)),
                                        model = image,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    },
                    trailingContent = {
                        if (images.size > 3) {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(secondaryColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier.padding(5.dp),
                                    text = "+${images.size - 3}", color = Color.Black
                                )
                            }

                        }
                    }
                )


//                LazyRow(
//                ) {
//                    itemsIndexed(images) {index, url ->
//                        AsyncImage(
//                            modifier = Modifier
//                                .clip(RoundedCornerShape(20.dp))
//                                .offset(
//                                    x = if (index == 0) 0.dp else (10).dp,
//                                )
//                                .padding(start = 10.dp),
//                            model = url,
//                            contentDescription = null
//                        )
//                    }
//                }

//                viewModel.analyseImage(context)
//                Spacer(modifier = Modifier.height(10.dp))

                imageToText?.let { text ->
                    Column(
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
private fun ImagePicker(
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

    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        onClick = { launcher.launch("image/*") }
    ) {
        Text(
            text = "Add Image",
            style = MaterialTheme.typography.bodyMedium
        )
    }


}
