package com.example.advance.navigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object AdjustScreen: Screen("adjust_screen")
}