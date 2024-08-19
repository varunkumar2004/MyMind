package com.varunkumar.mymind

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.common.util.JsonUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.varunkumar.mymind.presentation.HomeViewModel
import com.varunkumar.mymind.presentation.Routes
import com.varunkumar.mymind.presentation.bookmark.BookmarkScreen
import com.varunkumar.mymind.presentation.bookmark.ExploreScreen
import com.varunkumar.mymind.presentation.home.HomeScreen
import com.varunkumar.mymind.presentation.search.SearchScreen
import com.varunkumar.mymind.ui.theme.CustomTypography
import com.varunkumar.mymind.ui.theme.MyMindTheme
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val sModifier = Modifier.fillMaxSize()
            val homeViewModel = hiltViewModel<HomeViewModel>()
            val navController = rememberNavController()
            var showAlert by remember { mutableStateOf(false) }

            MyMindTheme {
                if (showAlert) {
                    AlertDialog(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        onDismissRequest = { showAlert = false },
                        title = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(imageVector = Icons.Outlined.Close, contentDescription = null)
                                Text(
                                    text = "Something unexpected happened.",
                                    style = CustomTypography.bodyLarge
                                )
                            }
                        },
                        text = {
                            Text(
                                text = "Can't fetch the specific bookmark. The Bookmark might be deleted. Please reload the app.",
                                style = CustomTypography.bodyMedium
                            )
                        },
                        confirmButton = { showAlert = false }
                    )
                }

                NavHost(navController = navController, startDestination = Routes.Home.route) {
                    composable(route = Routes.Home.route) {
                        HomeScreen(
                            modifier = sModifier,
                            viewModel = homeViewModel,
                            onAddBookmarkButtonClick = {
                                navController.navigate(Routes.Bookmark.route + "/-1")
                            },
                            onSearchBookmarkButtonClick = {
                                navController.navigate(Routes.Search.route)
                            },
                            onBookmarkAction = { id ->
                                navController.navigate(Routes.Bookmark.route + "/$id")
                            }
                        )
                    }

                    composable(route = Routes.Search.route) {
                        SearchScreen(
                            modifier = sModifier,
                            onBookmarkAction = { id ->
                                navController.navigate(Routes.Bookmark.route + "/$id")
                            },
                            onBackButtonClick = { navController.navigateUp() }
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
                            BookmarkScreen(
                                modifier = sModifier,
                                id = bookmarkId,
                                onBackButtonClick = { navController.navigateUp() },
                                onImagesSelected = { images ->
                                    val jsonString = "\"${Gson().toJson(images)}\""

                                    Log.d("list navigation list", Routes.Explore.route + "/$jsonString")
                                    navController.navigate(Routes.Explore.route + "/$jsonString")
                                }
                            )
                        } else {
                            showAlert = true
                        }
                    }

                    composable(
                        route = Routes.Explore.route + "/{images}",
                        arguments = listOf(
                            navArgument("images") {
                                type = NavType.StringType
                            }
                        )
                    ) { backStackEntry ->
                        val images = backStackEntry.arguments?.getString("images")
//                        val listType = object : TypeToken<List<String>>() {}.type
//                        val list: List<String> = Gson().fromJson(images, listType)

                        Log.d("list navigation response", images.toString())

//                        images?.let {
//                            ExploreScreen(
//                                modifier = sModifier,
//                                images = list,
//                                onBackButtonClick = { navController.navigateUp() }
//                            )
//                        }
                    }
                }
            }
        }
    }
}