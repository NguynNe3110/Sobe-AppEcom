package com.uzuu.sobe.ui.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash")

    object Login: Screen("login")
    object Register: Screen("register")
    object ForgetPassword: Screen("forgetPassword")

    object Main: Screen("main")

    object Home: Screen("home")
    object Shopping: Screen("shopping")
    object Community: Screen("community")
    object Message: Screen("message")
    object Account: Screen("account")
}