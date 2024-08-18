package com.varunkumar.mymind

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.varunkumar.mymind.data.models.Bookmark
import com.varunkumar.mymind.presentation.BookmarksViewModel
import com.varunkumar.mymind.presentation.home.HomeScreen
import com.varunkumar.mymind.presentation.Routes
import com.varunkumar.mymind.presentation.bookmark.BookmarkScreen
import com.varunkumar.mymind.ui.theme.MyMindTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val sModifier = Modifier.fillMaxSize()
            val bookmarksViewModel = hiltViewModel<BookmarksViewModel>()
            val navController = rememberNavController()

            MyMindTheme {
                NavHost(navController = navController, startDestination = Routes.Home.route) {
                    composable(route = Routes.Home.route) {
                        HomeScreen(
                            modifier = sModifier,
                            viewModel = bookmarksViewModel,
                            onAddBookmarkButtonClick = {
//                                navController.navigate(Routes.Bookmark.route + "/-1")
                                bookmarksViewModel.insert(
                                    bookmark = Bookmark(
                                        title = "New Bookmark",
                                        snippetText = "This is a new bookmark"
                                    )
                                )
                            },
                            onBookmarkAction = { id ->
                                Log.d("bookmark id", id.toString())
                                navController.navigate(Routes.Bookmark.route + "/$id")
                            }
                        )
                    }

                    composable(
                        route = Routes.Bookmark.route + "/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->
                        val bookmarkId = backStackEntry.arguments?.getInt("id")

                        if (bookmarkId != null) {
                            bookmarksViewModel.getBookmarkById(bookmarkId)
                            val bookmarkState by bookmarksViewModel.state.collectAsState()

                            bookmarkState.bookmark?.let { bookmark ->
                                BookmarkScreen(
                                    modifier = sModifier,
                                    bookmark = bookmark,
                                    viewModel = bookmarksViewModel,
                                    onBackButtonClick = {
                                        navController.navigateUp()
                                    }
                                )
                            } ?: run {
                                // Handle the case where bookmark is null, e.g., show an error message
                                Text("Bookmark not found")
                            }
                        }
                    }
                }
            }
        }
    }
}