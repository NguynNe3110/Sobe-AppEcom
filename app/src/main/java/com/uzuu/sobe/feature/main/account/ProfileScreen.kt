package com.uzuu.sobe.feature.main.account

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush
import com.example.ui.theme.AppColor
import com.uzuu.sobe.R
import com.uzuu.sobe.domain.model.ProductItem
import com.uzuu.sobe.domain.model.init.listProducts
import com.uzuu.sobe.ui.component.ItemList
import com.uzuu.sobe.ui.component.ProductCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    windowSizeClass: WindowSizeClass,
    onNavigateToOrders: () -> Unit,
    onNavigateToWardrobe: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToRewards: () -> Unit,
    onNavigateToWallet: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onProductClick: (ProductItem) -> Unit
) {
    // ✅ Logic responsive giống HomeScreen
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            ProfileCompactContent(
                onNavigateToOrders = onNavigateToOrders,
                onNavigateToWardrobe = onNavigateToWardrobe,
                onNavigateToFavorites = onNavigateToFavorites,
                onNavigateToRewards = onNavigateToRewards,
                onNavigateToWallet = onNavigateToWallet,
                onNavigateToSettings = onNavigateToSettings,
                onProductClick = onProductClick
            )
        }
        WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
            ProfileExpandedContent(
                onNavigateToOrders = onNavigateToOrders,
                onNavigateToWardrobe = onNavigateToWardrobe,
                onNavigateToFavorites = onNavigateToFavorites,
                onNavigateToRewards = onNavigateToRewards,
                onNavigateToWallet = onNavigateToWallet,
                onNavigateToSettings = onNavigateToSettings,
                onProductClick = onProductClick
            )
        }
    }
}

// ✅ Giao diện cho Mobile (Compact)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCompactContent(
    onNavigateToOrders: () -> Unit,
    onNavigateToWardrobe: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToRewards: () -> Unit,
    onNavigateToWallet: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onProductClick: (ProductItem) -> Unit
) {
    val recentlyViewedProducts = listProducts

    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(AppColor.textSage1000)
        ) {
            // Top App Bar
            TopAppBar(
                title = {
                    Text(
                        text = "Cộng đồng",
                        style = AppTextStyles.Heading1 + TextStyle(AppBrush.SageGradient),
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = { /* Search */ }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_cart),
                            contentDescription = "Cart",
                            modifier = Modifier.size(17.dp),
                            tint = Color.Unspecified
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )

            // User Profile Section
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Avatar
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_product),
                            contentDescription = "User Avatar",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // User Info
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "nguyetong51",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row {
                            Text(
                                text = "99 Người theo dõi",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "50 Đang theo dõi",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }

                    // Rating Badge
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                color = Color(0xFFFFF9E6),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = "Rating",
                            tint = Color(0xFFFFB800),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "4.9",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF666666)
                        )
                    }
                }
            }

            // Menu Grid
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        MenuItem(
                            icon = R.drawable.ic_shopping_unselected,
                            label = "Đơn mua",
                            onClick = onNavigateToOrders
                        )
                        MenuItem(
                            icon = R.drawable.ic_myclother,
                            label = "Tủ đồ",
                            onClick = onNavigateToWardrobe
                        )
                        MenuItem(
                            icon = R.drawable.ic_myheart,
                            label = "Yêu thích",
                            onClick = onNavigateToFavorites
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        MenuItem(
                            icon = R.drawable.ic_myreward,
                            label = "Thưởng",
                            onClick = onNavigateToRewards
                        )
                        MenuItem(
                            icon = R.drawable.ic_mywallet,
                            label = "Ví",
                            onClick = onNavigateToWallet
                        )
                        MenuItem(
                            icon = R.drawable.ic_mysetting,
                            label = "Cài đặt",
                            onClick = onNavigateToSettings
                        )
                    }
                }
            }

            // Recently Viewed Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .background(Color.White)
            ) {
                Text(
                    text = "Đã xem gần đây",
                    style = AppTextStyles.Heading3,
                    color = AppColor.neutral300,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(recentlyViewedProducts) { product ->
                        ProductCard(
                            product = product,
                            onClick = { onProductClick(product) }
                        )
                    }
                }
            }

            // Invite Friends Section
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color.White
            ) {
                Column {
                    ItemList(text = "Mời bạn bè tham gia", onClick = {})
                    ItemList(text = "Mời bạn bè tham gia", onClick = {})
                    ItemList(text = "Mời bạn bè tham gia", onClick = {})
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// ✅ Giao diện cho Tablet / Foldable (Medium & Expanded)
// Hiện tại đang để giống Compact, sau này bạn có thể sửa lại layout 2 cột (2-pane) ở đây
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileExpandedContent(
    onNavigateToOrders: () -> Unit,
    onNavigateToWardrobe: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToRewards: () -> Unit,
    onNavigateToWallet: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onProductClick: (ProductItem) -> Unit
) {
    val recentlyViewedProducts = listProducts

    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(AppColor.textSage1000)
        ) {
            // Top App Bar
            TopAppBar(
                title = {
                    Text(
                        text = "Cộng đồng",
                        style = AppTextStyles.Heading1 + TextStyle(AppBrush.SageGradient),
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = { /* Search */ }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_cart),
                            contentDescription = "Cart",
                            modifier = Modifier.size(17.dp),
                            tint = Color.Unspecified
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )

            // User Profile Section
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_product),
                            contentDescription = "User Avatar",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "nguyetong51", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        Spacer(modifier = Modifier.height(4.dp))
                        Row {
                            Text(text = "99 Người theo dõi", fontSize = 12.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "50 Đang theo dõi", fontSize = 12.sp, color = Color.Gray)
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.background(color = Color(0xFFFFF9E6), shape = RoundedCornerShape(16.dp)).padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_star), contentDescription = "Rating", tint = Color(0xFFFFB800), modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "4.9", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF666666))
                    }
                }
            }

            // Menu Grid
            Surface(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), color = Color.White) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        MenuItem(icon = R.drawable.ic_shopping_unselected, label = "Đơn mua", onClick = onNavigateToOrders)
                        MenuItem(icon = R.drawable.ic_myclother, label = "Tủ đồ", onClick = onNavigateToWardrobe)
                        MenuItem(icon = R.drawable.ic_myheart, label = "Yêu thích", onClick = onNavigateToFavorites)
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        MenuItem(icon = R.drawable.ic_myreward, label = "Thưởng", onClick = onNavigateToRewards)
                        MenuItem(icon = R.drawable.ic_mywallet, label = "Ví", onClick = onNavigateToWallet)
                        MenuItem(icon = R.drawable.ic_mysetting, label = "Cài đặt", onClick = onNavigateToSettings)
                    }
                }
            }

            // Recently Viewed Section
            Column(modifier = Modifier.fillMaxWidth().padding(top = 24.dp).background(Color.White)) {
                Text(text = "Đã xem gần đây", style = AppTextStyles.Heading3, color = AppColor.neutral300, modifier = Modifier.padding(horizontal = 16.dp))
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(recentlyViewedProducts) { product ->
                        ProductCard(product = product, onClick = { onProductClick(product) })
                    }
                }
            }

            // Invite Friends Section
            Surface(modifier = Modifier.fillMaxWidth().padding(16.dp), color = Color.White) {
                Column {
                    ItemList(text = "Mời bạn bè tham gia", onClick = {})
                    ItemList(text = "Mời bạn bè tham gia", onClick = {})
                    ItemList(text = "Mời bạn bè tham gia", onClick = {})
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun MenuItem(
    @DrawableRes icon: Int,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = label,
                tint = Color(0xFF666666),
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF666666)
        )
    }
}

// ==========================================
// ✅ PREVIEW CHO TỪNG THIẾT BỊ
// ==========================================

// Preview cho Phone (Compact)
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    name = "Phone - Portrait",
    device = Devices.PIXEL_5,
    showBackground = true
)
@Composable
fun PreviewProfileScreen_Phone() {
    ProfileScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(width = 393.dp, height = 851.dp)
        ),
        onNavigateToOrders = {},
        onNavigateToWardrobe = {},
        onNavigateToFavorites = {},
        onNavigateToRewards = {},
        onNavigateToWallet = {},
        onNavigateToSettings = {},
        onProductClick = {}
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
fun PreviewProfileScreen_Foldable() {
    ProfileScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(width = 673.dp, height = 841.dp) // Kích thước chuẩn cho Medium
        ),
        onNavigateToOrders = {},
        onNavigateToWardrobe = {},
        onNavigateToFavorites = {},
        onNavigateToRewards = {},
        onNavigateToWallet = {},
        onNavigateToSettings = {},
        onProductClick = {}
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
fun PreviewProfileScreen_Tablet() {
    ProfileScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(width = 1280.dp, height = 800.dp) // Kích thước chuẩn cho Expanded
        ),
        onNavigateToOrders = {},
        onNavigateToWardrobe = {},
        onNavigateToFavorites = {},
        onNavigateToRewards = {},
        onNavigateToWallet = {},
        onNavigateToSettings = {},
        onProductClick = {}
    )
}