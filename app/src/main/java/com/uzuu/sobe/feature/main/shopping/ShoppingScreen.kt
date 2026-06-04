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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sobe.ui.theme.AppTextStyles
import com.uzuu.sobe.R
import com.uzuu.sobe.domain.model.ProductItem
import com.uzuu.sobe.domain.model.init.listProducts

// Data class


@Composable
fun ShoppingScreen(
    onProductClick: (String) -> Unit
) {
    val products = listProducts
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Tất cả", "Thời trang nữ", "Thời trang nam", "Thời trang trẻ em")

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
            divider = { }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            style = AppTextStyles.Heading4,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == index) Color(0xFF4CAF50) else Color.Gray
                        )
                    },
                    modifier = Modifier.padding(horizontal = 8.dp)
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

@Composable
private fun ProductCard(
    product: ProductItem,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        // Product Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(Color(0xFFF5F5F5))
        ) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Heart/Wishlist button
            Icon(
                painter = painterResource(id = R.drawable.ic_heart_outline),
                contentDescription = "Wishlist",
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .background(Color.White.copy(alpha = 0.7f), CircleShape)
                    .padding(4.dp)
            )
        }

        // Product Info
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = product.name,
                fontSize = 12.sp,
                color = Color.Black,
                maxLines = 2,
                modifier = Modifier.height(32.dp)
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = product.price,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE91E63)
            )

            Spacer(Modifier.height(6.dp))

            // Seller Info
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = product.sellerAvatar),
                    contentDescription = product.sellerName,
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(Modifier.width(4.dp))

                Text(
                    text = product.sellerName,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
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