package com.uzuu.sobe.feature.main.baseMain

import android.app.Activity
import com.uzuu.sobe.R
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui.theme.AppBrush
import com.uzuu.jetpack_compose_hub.feature.home.HomeScreen
import com.uzuu.sobe.feature.main.account.ProfileScreen
import com.uzuu.sobe.feature.main.community.CommunityScreen
import com.uzuu.sobe.feature.main.message.MessageScreen
import com.uzuu.sobe.feature.main.shopping.ShoppingScreen
import com.uzuu.sobe.ui.navigation.Screen

// 1. DATA MODEL: Định nghĩa rõ ràng Icon được chọn và chưa được chọn
// 1. DATA MODEL
data class BottomNavItem(
    val title: String,
    @DrawableRes val selectedIcon: Int, // Thay ImageVector bằng Int (Drawable Resource ID)
    @DrawableRes val unselectedIcon: Int,
    val route: String
)

// 2. DANH SÁCH ITEMS: Ánh xạ đúng với Screen.route của bạn
val bottomNavItems = listOf(
    BottomNavItem("Trang chủ",  R.drawable.ic_home_selected, R.drawable.ic_home_unselected, Screen.Home.route),
    BottomNavItem("Mua sắm", R.drawable.ic_shopping_selected, R.drawable.ic_shopping_unselected, Screen.Shopping.route),
    BottomNavItem("Cộng đồng", R.drawable.ic_community_selected, R.drawable.ic_community_unselected, Screen.Community.route),
    BottomNavItem("Tin nhắn", R.drawable.ic_chat_selected, R.drawable.ic_chat_unselected, Screen.Message.route),
    BottomNavItem("Tài khoản", R.drawable.ic_profile_selected, R.drawable.ic_profile_unselected, Screen.Account.route)
)

//@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
//@Composable
//fun MainRoute() {
//    // 1. Lấy Context từ LocalContext và ép kiểu sang Activity
//    val activity = LocalContext.current as Activity
//
//    // 2. Tính toán kích thước cửa sổ dựa trên Activity
//    // Nếu activity null (hiếm khi xảy ra nếu chạy đúng cách), ta dùng giá trị mặc định hoặc xử lý an toàn
//    val windowSizeClass = calculateWindowSizeClass(activity)
//    MainScreen(windowSizeClass)
//}

// 3. MAIN SCREEN: Ghép nối Scaffold, NavHost và Custom Bottom Bar
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MainScreen(
    windowSizeClass: WindowSizeClass
) {
    val navController = rememberNavController()

    // 3. Lấy route hiện tại
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route

    // 4. Quyết định loại Navigation dựa trên Width
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            ScaffoldWithBottomBar(
                items = bottomNavItems,
                currentRoute = currentRoute,
                navController = navController
            ) { innerPadding ->
                NavHostContent(
                    navController = navController,
                    modifier = innerPadding,
                    windowSizeClass = windowSizeClass
                )
            }
        }
        WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
            ScaffoldWithNavigationRail(
                items = bottomNavItems,
                currentRoute = currentRoute,
                navController = navController
            ) { innerPadding ->
                NavHostContent(
                    navController = navController,
                    modifier = innerPadding,
                    windowSizeClass = windowSizeClass
                )
            }
        }
    }
}

// --- Helper Functions để giữ MainScreen sạch ---

@Composable
private fun ScaffoldWithBottomBar(
    items: List<BottomNavItem>,
    currentRoute: String,
    navController: NavHostController,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        bottomBar = {
            CustomBottomBarAnimated(
                items = items,
                currentRoute = currentRoute,
                onItemClick = { item ->
                    navigateToTab(navController, item.route)
                }
            )
        }
    ) { innerPadding ->
        content(Modifier.padding(innerPadding))
    }
}

@Composable
private fun ScaffoldWithNavigationRail(
    items: List<BottomNavItem>,
    currentRoute: String,
    navController: NavHostController,
    content: @Composable (Modifier) -> Unit
) {
    // Bạn cần tạo thêm hàm CustomNavigationRail tương tự CustomBottomBarAnimated
    // Nhưng dùng component NavigationRail của Material3
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Row(modifier = Modifier.fillMaxSize()) {
            CustomNavigationRailAnimated( // Hàm này bạn cần tự viết hoặc tôi sẽ cung cấp mẫu bên dưới
                items = items,
                currentRoute = currentRoute,
                onItemClick = { item ->
                    navigateToTab(navController, item.route)
                }
            )

            // Nội dung chính
            Box(modifier = Modifier.weight(1f)) {
                content(Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
private fun NavHostContent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToSearch = {},
                onNavigateToCart = {},
                onNavigateToCategory = {},
                onNavigatteToCateGoryAll = {},
                onNavigateToProductDetail = {}
            )
        }
        composable(Screen.Shopping.route) {
            ShoppingScreen(onProductClick = {})
        }
        composable(Screen.Community.route) {
            CommunityScreen(onPostClick = {}, onNavigateToPost = {})
        }
        composable(Screen.Message.route) {
            MessageScreen()
        }
        composable(Screen.Account.route) {
            ProfileScreen(
                onNavigateToOrders = {},
                onNavigateToWardrobe = {},
                onNavigateToFavorites = {},
                onNavigateToRewards = {},
                onNavigateToWallet = {},
                onNavigateToSettings = {},
                onProductClick = {}
            )
        }
    }
}

// Hàm hỗ trợ điều hướng chung
private fun navigateToTab(navController: NavHostController, route: String) {
    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}


// Preview cho Phone (Compact)
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    name = "Phone - Portrait",
    device = Devices.PIXEL_5,
    showBackground = true
)
@Composable
fun PreviewMainScreen_Phone() {
    MainScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(width = 393.dp, height = 851.dp)
        )
    )
}

// Preview cho Tablet (Expanded)
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    name = "Tablet - Landscape",
    device = Devices.PIXEL_C,
    showBackground = true
)
@Composable
fun PreviewMainScreen_Tablet() {
    MainScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(width = 900.dp, height = 1280.dp)
        )
    )
}

// Preview cho Foldable (Medium)
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    name = "Foldable",
    device = Devices.FOLDABLE,
    showBackground = true
)
@Composable
fun PreviewMainScreen_Foldable() {
    MainScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(width = 673.dp, height = 841.dp)
        )
    )
}