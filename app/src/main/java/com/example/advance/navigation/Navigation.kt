package com.example.advance.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.advance.view.Adjust
import com.example.advance.view.Home
import com.example.advance.viewModel.ActivityViewModel

@Composable
fun Navigation(viewModel: ActivityViewModel = viewModel(), navController: NavHostController = rememberNavController()){
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route ){

        composable(Screen.HomeScreen.route){
            Home(navController, viewModel)
        }

        composable(Screen.AdjustScreen.route){
            Adjust(id = 0, viewModel = viewModel, navController = navController)
        }
    }
}