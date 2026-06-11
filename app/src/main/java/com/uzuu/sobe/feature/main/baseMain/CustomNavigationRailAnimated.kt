package com.uzuu.sobe.feature.main.baseMain

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
@Composable
fun CustomNavigationRailAnimated(
    items: List<BottomNavItem>,
    currentRoute: String,
    onItemClick: (BottomNavItem) -> Unit
) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.route
                NavigationRailItemAnimated(
                    item = item,
                    isSelected = isSelected,
                    onClick = { onItemClick(item) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun NavigationRailItemAnimated(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // Animation cho kích thước icon
    val iconSize by animateDpAsState(
        targetValue = if (isSelected) 24.dp else 22.dp,
        label = "rail_icon_size"
    )

    // Animation cho chiều cao (Tạo hiệu ứng giãn ra theo chiều dọc)
    val itemHeight by animateDpAsState(
        targetValue = if (isSelected) 72.dp else 56.dp,
        label = "rail_item_height"
    )

    // Animation cho màu sắc
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
        label = "rail_content_color"
    )

    Column(
        modifier = Modifier
            .width(64.dp)
            .height(itemHeight)
            .clickable { onClick() }
            .clip(RoundedCornerShape(16.dp))
            .then(
                if (isSelected) {
                    Modifier.background(AppBrush.SageGradient)
                } else {
                    Modifier
                }
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(
                id = if (isSelected) item.selectedIcon else item.unselectedIcon
            ),
            contentDescription = item.title,
            tint = Color.Unspecified,
            modifier = Modifier.size(iconSize)
        )

        AnimatedVisibility(
            visible = isSelected,
            enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
            exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Top)
        ) {
            Text(
                text = item.title,
                color = contentColor,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(top = 4.dp),
                maxLines = 1
            )
        }
    }
}