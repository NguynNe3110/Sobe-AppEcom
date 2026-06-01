package com.uzuu.sobe.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uzuu.jetpack_compose_hub.feature.learn6_navigation.feature.auth.login.LoginScreen
import com.uzuu.jetpack_compose_hub.feature.learn6_navigation.feature.auth.register.RegisterScreen
import com.uzuu.sobe.feature.main.MainScreen

// Auth Graph
fun NavGraphBuilder.authGraph(navController: NavController) {
    composable(Screen.Login.route) {
        LoginScreen(
            onNavigateToRegister = {
                navController.navigate(Screen.Register.route)
            },
            onLoginSuccess = {
                navController.navigate(Screen.Main.route) {
                    popUpTo(
                        Screen.Login.route
                    ) {
                        inclusive = true
                    }
                }
            }
        )
    }

    composable(Screen.Register.route) {
        RegisterScreen(
            onNavigateBack = {
                navController.popBackStack()
            },
            onRegisterSuccess = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Register.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
}

// Main Graph - chỉ có MainScreen với Bottom Navigation
fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable(Screen.Main.route) {
        MainScreen(
            onLogout = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            }
        )
    }
}