package com.varunkumar.mymind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.varunkumar.mymind.presentation.HomeScreen
import com.varunkumar.mymind.ui.theme.MyMindTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val sModifier = Modifier.fillMaxSize()

            MyMindTheme {
                HomeScreen(
                    modifier = sModifier,
                    onSearchAction = {

                    }
                )
            }
        }
    }
}