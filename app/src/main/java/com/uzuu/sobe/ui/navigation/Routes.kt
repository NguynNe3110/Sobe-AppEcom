package com.uzuu.sobe.ui.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash")
    object Welcome: Screen("welcome")


    //auth
    object Login: Screen("login")

    object ConfirmRegister: Screen("confirmregister")
    object Register: Screen("register")
    object EndRegister: Screen("endregister")

    object StartForget: Screen("startforget")
    object MiddleForget: Screen("middleforget")
    object ResetPassword: Screen("resetpassword")
    object EndForget: Screen("endforget")
    //
    object Main: Screen("main")


    //main
    object Home: Screen("home")
    object Shopping: Screen("shopping")
    object Community: Screen("community")
    object Message: Screen("message")
    object Account: Screen("account")
}