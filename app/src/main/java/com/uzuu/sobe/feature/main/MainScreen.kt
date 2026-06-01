package com.uzuu.sobe.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uzuu.sobe.R
import com.uzuu.sobe.feature.main.account.AccountScreen
import com.uzuu.sobe.feature.main.community.CommunityScreen
import com.uzuu.sobe.feature.main.home.HomeScreen
import com.uzuu.sobe.feature.main.message.MessageScreen
import com.uzuu.sobe.feature.main.shopping.ShoppingScreen
import com.uzuu.sobe.ui.icon.AppIcons
import com.uzuu.sobe.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onLogout: () -> Unit
) {
    // NavController riêng cho Bottom Navigation
    val bottomNavController = rememberNavController()

    // Các items trong Bottom Navigation
    val bottomNavItems = listOf(
        BottomNavItem(
            name = "Trang chủ",
            route = Screen.Home.route,
            selectedIcon =  R.drawable.ic_home,
            unselectedIcon = AppIcons.Navigation.Home
        ),
//        BottomNavItem(
//            name = "Setting",
//            route = Screen.Shopping.route,
//            selectedIcon = AppIcons.Navigation.Home,
//            unselectedIcon = Icons.Outlined.Settings
//        ),
//        BottomNavItem(
//            name = "Profile",
//            route = Screen.Profile.route,
//            selectedIcon = Icons.Filled.Person,
//            unselectedIcon = Icons.Outlined.Person
//        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = if (currentRoute == item.route) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = item.name
                            )
                        },
                        label = { Text(text = item.name) },
                        selected = currentRoute == item.route,
                        onClick = {
                            bottomNavController.navigate(item.route) {
                                popUpTo(bottomNavController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = bottomNavController,
                startDestination = Screen.Home.route
            ) {
                composable(Screen.Home.route) {
                    HomeScreen(onLogout = onLogout)
                }
                composable(Screen.Shopping.route) {
                    ShoppingScreen()
                }
                composable(Screen.Community.route) {
                    CommunityScreen()
                }
                composable(Screen.Message.route) {
                    MessageScreen()
                }
                composable(Screen.Account.route) {
                    AccountScreen()
                }
            }
        }
    }
}

// Data class cho Bottom Navigation Item
data class BottomNavItem(
    val name: String,
    val route: String,
    val selectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
    val unselectedIcon: androidx.compose.ui.graphics.vector.ImageVector
)
