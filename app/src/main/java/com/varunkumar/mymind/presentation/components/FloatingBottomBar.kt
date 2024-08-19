package com.varunkumar.mymind.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.varunkumar.mymind.ui.theme.primaryColor

@Composable
fun FloatingBottomBar(
    onAddButtonClick: () -> Unit, onSearchButtonClick: () -> Unit
) {
    val shape = RoundedCornerShape(40.dp)

    Row(
        modifier = Modifier
            .shadow(
                elevation = 25.dp,
                shape = shape
            )
            .background(Color.Black)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilledIconButton(
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Color.White
            ),
            onClick = onSearchButtonClick
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Black
            )
        }

        FilledIconButton(
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            onClick = onAddButtonClick
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }
    }
}