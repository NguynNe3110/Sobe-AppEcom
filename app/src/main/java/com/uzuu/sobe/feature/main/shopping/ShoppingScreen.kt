package com.uzuu.sobe.feature.main.shopping

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush
import com.example.ui.theme.AppColor
import com.uzuu.sobe.R
import com.uzuu.sobe.domain.model.ProductItem
import com.uzuu.sobe.domain.model.init.listProducts
import com.uzuu.sobe.ui.component.ProductCard

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ShoppingScreen(
    windowSizeClass: WindowSizeClass,
    onProductClick: (String) -> Unit
) {
    val products = listProducts
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Tất cả", "Thời trang nữ", "Thời trang nam", "Thời trang trẻ em")

    // ✅ LOGIC RESPONSIVE: Quyết định cấu trúc layout dựa trên Size Class
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            ShoppingCompactContent(
                tabs = tabs,
                selectedTab = selectedTab,
                products = products,
                onTabSelected = { selectedTab = it },
                onProductClick = onProductClick
            )
        }
        WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
            ShoppingExpandedContent(
                tabs = tabs,
                selectedTab = selectedTab,
                products = products,
                onTabSelected = { selectedTab = it },
                onProductClick = onProductClick
            )
        }
    }
}

// 🟢 LAYOUT CHO MOBILE (COMPACT)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCompactContent(
    tabs: List<String>,
    selectedTab: Int,
    products: List<ProductItem>,
    onTabSelected: (Int) -> Unit,
    onProductClick: (String) -> Unit
) {
    Scaffold(
        topBar = { ShoppingTopAppBar() },
        containerColor = Color.White
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 90.dp, top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                TabSection(tabs = tabs, selectedTab = selectedTab, onTabSelected = onTabSelected)
            }
            items(products) { product ->
                ProductCard(product = product, onClick = { onProductClick(product.name) })
            }
        }
    }
}

// 🔵 LAYOUT CHO TABLET (MEDIUM & EXPANDED)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingExpandedContent(
    tabs: List<String>,
    selectedTab: Int,
    products: List<ProductItem>,
    onTabSelected: (Int) -> Unit,
    onProductClick: (String) -> Unit
) {
    // 💡 Mẹo: Tôi đã tinh chỉnh lại một chút thông số cho Tablet
    // (tăng minSize của Grid và padding) để giao diện thoáng hơn trên màn hình lớn.
    Scaffold(
        topBar = { ShoppingTopAppBar() },
        containerColor = Color.White
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 180.dp), // ✅ Tăng kích thước tối thiểu của item
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(paddingValues)
                .padding(horizontal = 24.dp), // ✅ Tăng padding ngang
            contentPadding = PaddingValues(bottom = 90.dp, top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                TabSection(tabs = tabs, selectedTab = selectedTab, onTabSelected = onTabSelected)
            }
            items(products) { product ->
                ProductCard(product = product, onClick = { onProductClick(product.name) })
            }
        }
    }
}

// 🔧 HÀM TÁCH RIÊNG: Giúp 2 layout trên không bị lặp code TopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShoppingTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Mua sắm",
                style = AppTextStyles.Heading1 + TextStyle(AppBrush.SageGradient),
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            Row {
                IconButton(onClick = { /* Search */ }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search_gr),
                        contentDescription = "Search",
                        modifier = Modifier.size(17.dp),
                        tint = Color.Unspecified
                    )
                }
                IconButton(onClick = { /* Cart */ }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_cart),
                        contentDescription = "Cart",
                        modifier = Modifier.size(17.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}

// ✅ ĐÃ SỬA LỖI: Thêm callback onTabSelected để cập nhật state
@Composable
fun TabSection(
    tabs: List<String>,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit // 👈 Thêm tham số này
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        edgePadding = 0.dp,
        containerColor = Color.White,
        contentColor = Color.Black,
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) }, // 👈 Gọi callback thay vì gán trực tiếp
                text = {
                    Text(
                        text = title,
                        style = AppTextStyles.Heading4,
                        color = if (selectedTab == index) AppColor.Primary else AppColor.textSage100,
                        textDecoration = if (selectedTab == index) TextDecoration.Underline else TextDecoration.None
                    )
                },
            )
        }
    }
}

// --- PREVIEW ---

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(name = "Phone - Portrait", device = Devices.PIXEL_5, showBackground = true)
@Composable
fun PreviewShoppingScreen_Phone() {
    ShoppingScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(393.dp, 851.dp)),
        onProductClick = {}
    )
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(name = "Tablet - Landscape", device = Devices.PIXEL_C, showBackground = true)
@Composable
fun PreviewShoppingScreen_Tablet() {
    ShoppingScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(900.dp, 1280.dp)),
        onProductClick = {}
    )
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(name = "Foldable", device = Devices.FOLDABLE, showBackground = true)
@Composable
fun PreviewShoppingScreen_Foldable() {
    ShoppingScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(673.dp, 841.dp)),
        onProductClick = {}
    )
}