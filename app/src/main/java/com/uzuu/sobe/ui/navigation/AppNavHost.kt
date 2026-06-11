package com.uzuu.sobe.ui.navigation

import android.util.Log
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.uzuu.sobe.feature.auth.welcome.WelcomeScreen
import com.uzuu.sobe.feature.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    windowSizeClass: WindowSizeClass,
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
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onRegisterClick = {
                    Log.d("DEBUG", "5555 in composable register")

                    navController.navigate(Screen.Register.route)
                },
                onLoginClick = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        // Auth Graph
        authGraph(navController)

        // Main Graph
        mainGraph(
            navController = navController,
            windowSizeClass = windowSizeClass)
    }
}