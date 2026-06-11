package com.uzuu.sobe.ui.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uzuu.jetpack_compose_hub.feature.learn6_navigation.feature.auth.login.LoginScreen
import com.uzuu.sobe.feature.auth.forgetPassword.MiddleForgetScreen
import com.uzuu.sobe.feature.auth.forgetPassword.ResetPasswordScreen
import com.uzuu.sobe.feature.auth.forgetPassword.StartForgetScreen
import com.uzuu.sobe.feature.auth.register.EndRegisterScreen
import com.uzuu.sobe.feature.auth.register.RegisterScreen
import com.uzuu.sobe.feature.auth.register.confirmRegister.ConfirmRegisterScreen
import com.uzuu.sobe.feature.main.baseMain.MainRoute
import com.uzuu.sobe.feature.main.baseMain.MainScreen

// Auth Graph
fun NavGraphBuilder.authGraph(navController: NavController) {
    composable(Screen.Login.route) {
        LoginScreen(
            onNavigateToRegister = {
                navController.navigate(Screen.Register.route)
            },
            onNavigateToHome = {
                navController.navigate(Screen.Main.route) {
                    popUpTo(
                        Screen.Login.route
                    ) {
                        inclusive = true
                    }
                }
            },
            onNavigateToForgetPassword = {

            }
        )
    }

    // register

    composable(Screen.Register.route) {
        RegisterScreen(
//            onNavigateBack = {
//                navController.popBackStack()
//            },
            onNavigateToLogin = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Register.route) {
                        inclusive = true
                    }
                }
            },
            onNavigateToConfirm = {
                Log.d("DEBUG", "innavgraph in composable register")
                navController.navigate(Screen.ConfirmRegister.route)
            },
        )
    }

    composable(Screen.ConfirmRegister.route) {
        ConfirmRegisterScreen(
            onClickComfirm = {
                navController.navigate(Screen.EndRegister.route)
            },
            onRequestAgain = {
                // todo code
            }
        )
    }

    composable(Screen.EndRegister.route) {
        EndRegisterScreen(
            onNavigationToHome = {
                navController.navigate(Screen.Login.route){
                    popUpTo(
                        Screen.EndRegister.route
                    ) {
                        inclusive = true
                    }
                }
            }
        )
    }

    //forget

    composable(Screen.StartForget.route) {
        StartForgetScreen(
            onNavigateToOTPScreen = {
                navController.navigate(Screen.MiddleForget.route)
            }
        )
    }

    composable(Screen.MiddleForget.route) {
        MiddleForgetScreen(
            onRequestAgain = {
                // todo code
            },
            onBackClick = {

            },
            onClickConfirm = {
                navController.navigate(Screen.ResetPassword.route)
            }
        )
    }

    composable(Screen.ResetPassword.route) {
        ResetPasswordScreen(
            onClickConfirmToEnd = {
                navController.navigate(Screen.EndForget.route)
            }
        )
    }

    composable(Screen.EndForget.route) {
        EndRegisterScreen(
            onNavigationToHome = {
                navController.navigate(Screen.Home.route)
            }
        )
    }
}

// Main Graph - chỉ có MainScreen với Bottom Navigation
fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable(Screen.Main.route) {
        MainRoute()
    }
}