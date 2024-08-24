package com.example.mztodo.ui.screens.routes

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mztodo.ui.screens.splash.SplashScreen
import com.example.mztodo.ui.screens.todo_list.TodoListScreen

@Composable
fun AppRoutes(
    modifier: Modifier,
    navHostController: NavHostController,
    startDestination: String = NavigationRoutes.Splash.route
) {
    val noEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
        {
            EnterTransition.None
        }

    val noExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        ExitTransition.None
    }

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination,
        enterTransition = noEnterTransition,
        exitTransition = noExitTransition,
        builder = {
            composable(route = NavigationRoutes.Splash.route) {
                SplashScreen {
                    navHostController.navigate(NavigationRoutes.TodoList.route)
                }
            }

            composable(route = NavigationRoutes.TodoList.route) {
                TodoListScreen {

                }
            }
        })
}
