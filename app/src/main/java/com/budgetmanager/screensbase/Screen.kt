package com.budgetmanager.screensbase

sealed class Screen (val route: String, val title: String) {

    object Home : Screen (route = "home", title = "Домашний экран")
    object Obligations : Screen (route = "obligations", title = "Обязательства")
}