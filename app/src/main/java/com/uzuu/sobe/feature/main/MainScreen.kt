package com.uzuu.sobe.feature.main

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.GpsFixed
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui.theme.AppBrush
import com.example.ui.theme.AppColor
import com.uzuu.sobe.feature.main.account.AccountScreen
import com.uzuu.sobe.feature.main.community.CommunityScreen
import com.uzuu.sobe.feature.main.home.HomeScreen
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

// 3. MAIN SCREEN: Ghép nối Scaffold, NavHost và Custom Bottom Bar
@Composable
fun MainScreen(onLogout: () -> Unit) {
    // NavController riêng cho các tab bên trong MainScreen
    val navController = rememberNavController()

    // Lấy route hiện tại để biết đang ở tab nào
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route

    Scaffold(
        // Đưa Custom Bottom Bar vào đây
        bottomBar = {
            CustomBottomBarAnimated(
                items = bottomNavItems,
                currentRoute = currentRoute,
                onItemClick = { item ->
                    // Xử lý điều hướng khi click
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        // Vùng hiển thị nội dung các màn hình con
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route
            ) {
                composable(Screen.Home.route) { HomeScreen(onLogout = onLogout) }
                composable(Screen.Shopping.route) { ShoppingScreen() }
                composable(Screen.Community.route) { CommunityScreen() }
                composable(Screen.Message.route) { MessageScreen() }
                composable(Screen.Account.route) { AccountScreen() }
            }
        }
    }
}

// 4. CONTAINER: Khung chứa thanh navigation
@Composable
fun CustomBottomBarAnimated(
    items: List<BottomNavItem>,
    currentRoute: String,
    onItemClick: (BottomNavItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp) // Tăng nhẹ chiều cao để đẹp hơn
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.route
                BottomBarItemAnimated(
                    item = item,
                    isSelected = isSelected,
                    onClick = { onItemClick(item) }
                )
            }
        }
    }
}

// 5. UI ITEM: Thành phần có Animation (Đã được tối ưu)
@Composable
fun BottomBarItemAnimated(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // Animation cho màu sắc
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
        label = "bg_color"
    )
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
        label = "content_color"
    )

    // Animation cho kích thước
    val iconSize by animateDpAsState(
        targetValue = if (isSelected) 28.dp else 24.dp,
        label = "icon_size"
    )

    // Animation cho chiều rộng (Tạo hiệu ứng giãn ra)
    val itemWidth by animateDpAsState(
        targetValue = if (isSelected) 110.dp else 56.dp,
        label = "item_width"
    )

    Row(
        modifier = Modifier
            .width(itemWidth)
            .height(40.dp)
            .clickable { onClick() }
            .clip(RoundedCornerShape(20.dp))
            .background(AppBrush.SageGradient), // THAY ĐỔI: Truyền Brush thay vì Color
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = if (isSelected) item.selectedIcon else item.unselectedIcon),
            contentDescription = item.title,
            tint = contentColor,
            modifier = Modifier.size(iconSize)
        )

        AnimatedVisibility(
            visible = isSelected,
            enter = fadeIn() + expandHorizontally(expandFrom = Alignment.Start),
            exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.Start)
        ) {
            Text(
                text = item.title,
                color = contentColor,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen(
        onLogout = {}
    )
}