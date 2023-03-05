package com.budgetmanager.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.budgetmanager.screens.HomeScreen
import com.budgetmanager.screensbase.Screen

@Composable
fun Navigation(navController: NavHostController, scaffoldState: ScaffoldState){
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
    }
}