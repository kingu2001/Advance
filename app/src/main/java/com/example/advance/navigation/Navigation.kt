package com.example.advance.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.advance.view.Adjust
import com.example.advance.view.Home
import com.example.advance.view.Login
import com.example.advance.viewModel.ActivityViewModel

@Composable
fun Navigation(
    activityViewModel: ActivityViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {

        composable(Screen.HomeScreen.route) {
            Home(navController, activityViewModel)
        }

        composable(Screen.LoginScreen.route
        ){
            Login(navController = navController)
        }

        composable(Screen.AdjustScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )) {
            entry -> val id = if (entry.arguments != null) entry.arguments!!.getInt("id") else 0
            Adjust(id = id, viewModel = activityViewModel, navController = navController)
        }
    }
}