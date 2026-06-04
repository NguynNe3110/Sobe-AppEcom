package com.uzuu.sobe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.uzuu.sobe.feature.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Splash Screen
        composable(Screen.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    // Giả lập kiểm tra đăng nhập
                    // Nếu đã login thì qua Home, chưa login thì qua Login
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // Auth Graph
        authGraph(navController)

        // Main Graph
        mainGraph(navController)
    }
}