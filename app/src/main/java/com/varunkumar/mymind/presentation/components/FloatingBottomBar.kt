package com.varunkumar.mymind.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.varunkumar.mymind.presentation.BookmarksViewModel
import com.varunkumar.mymind.ui.theme.primaryColor
import com.varunkumar.mymind.ui.theme.secondaryColor

@Composable
fun FloatingBottomBar(
    viewModel: BookmarksViewModel,
    onAddButtonClick: () -> Unit
) {
    val query by viewModel.searchQuery.collectAsStateWithLifecycle()
    val shape = RoundedCornerShape(20.dp)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Box(
            modifier = Modifier
                .shadow(
                    elevation = 20.dp,
                    shape = shape,
                    ambientColor = Color.LightGray
                )
        ) {
            TextField(
                value = query,
                onValueChange = viewModel::onSearchQueryChange,
                shape = shape,
                modifier = Modifier,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = primaryColor,
                    unfocusedContainerColor  = secondaryColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = { Text("Search") },
            )
        }

        Box(
            modifier = Modifier
                .shadow(
                    elevation = 20.dp,
                    shape = CircleShape,
                    ambientColor = Color.LightGray
                )
        ) {
            FilledIconButton(
                modifier = Modifier
                    .size(TextFieldDefaults.MinHeight),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = secondaryColor
                ),
                onClick = onAddButtonClick
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.Black
                )
            }
        }
    }
}