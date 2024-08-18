package com.varunkumar.mymind.presentation

sealed class Routes(val route: String) {
    data object Home: Routes("home")
    data object Bookmark: Routes("bookmark")
    data object Explore: Routes("explore")
}
