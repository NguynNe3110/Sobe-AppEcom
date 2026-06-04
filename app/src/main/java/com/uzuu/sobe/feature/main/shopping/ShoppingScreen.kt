package com.uzuu.sobe.feature.main.shopping

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush
import com.example.ui.theme.AppColor
import com.uzuu.sobe.R
import com.uzuu.sobe.domain.model.ProductItem
import com.uzuu.sobe.domain.model.init.listProducts
import com.uzuu.sobe.ui.component.ProductCard
import com.uzuu.sobe.ui.theme.AppDimens

// Data class

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingScreen(
    onProductClick: (String) -> Unit
) {
    val products = listProducts
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Tất cả", "Thời trang nữ", "Thời trang nam", "Thời trang trẻ em")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mua sắm",
                        style = AppTextStyles.Heading1 + TextStyle(AppBrush.SageGradient),
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    Row(

                    ) {
                        IconButton(onClick = { /* Search */ }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_search_gr),
                                contentDescription = "Cart",
                                modifier = Modifier.size(17.dp),
                                tint = Color.Unspecified
                            )
                        }
                        IconButton(onClick = { /* Search */ }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_cart),
                                contentDescription = "Cart",
                                modifier = Modifier.size(17.dp),
                                tint = Color.Unspecified
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },

        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
        ) {
            // Tabs
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                edgePadding = 0.dp,
                containerColor = Color.White,
                contentColor = Color.Black,
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
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

            // Products Grid - Luôn hiển thị cùng 1 list
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(products) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product.name) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShoppingScreen() {
    ShoppingScreen(
        onProductClick = {}
    )
}