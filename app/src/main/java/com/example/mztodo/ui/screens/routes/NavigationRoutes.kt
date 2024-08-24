package com.example.mztodo.ui.screens.routes

sealed class NavigationRoutes(val route: String) {
    data object Splash : NavigationRoutes(Splash::class.java.simpleName)
    data object TodoList : NavigationRoutes(TodoList::class.java.simpleName)
}