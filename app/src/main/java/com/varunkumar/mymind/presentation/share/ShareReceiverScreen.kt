package com.varunkumar.mymind.presentation.share

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Done
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.varunkumar.mymind.ui.theme.CustomTypography
import com.varunkumar.mymind.ui.theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareReceiverScreen(
    modifier: Modifier = Modifier,
    sharedText: String?,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    val systemTime = System.currentTimeMillis().toString()
    var title by remember { mutableStateOf("Bookmark $systemTime") }
    var content by remember { mutableStateOf(sharedText) }

    // TODO provide functionality for clickable links from text
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "New Bookmark",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onSave
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Done,
                                contentDescription = null
                            )
                        }

                        IconButton(
                            onClick = onCancel
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        },
        content = { innerPadding ->
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
                    value = title,
                    textStyle = MaterialTheme.typography.titleLarge,
                    onValueChange = { title = it }
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "content",
                    style = MaterialTheme.typography.bodySmall
                )

                content?.let { con ->
                    BasicTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = con,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        onValueChange = { content = it }
                    )
                }
            }
        }
    )
}