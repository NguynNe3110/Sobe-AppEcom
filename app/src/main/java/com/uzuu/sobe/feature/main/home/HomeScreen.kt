package com.uzuu.jetpack_compose_hub.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzuu.sobe.R
import com.uzuu.sobe.domain.model.ProductItem
import com.uzuu.sobe.domain.model.init.listCategories
import com.uzuu.sobe.domain.model.init.listProducts

// Data classes
data class HomeUiState(
    val searchQuery: String = "",
    val categories: List<CategoryItem> = listCategories,
    val products: List<ProductItem> = listProducts,
    val selectedTab: Int = 0
)

data class CategoryItem(
    val name: String,
    val image: Int
)



// Main Screen - Chỉ nhận callback điều hướng
@Composable
fun HomeScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToCategory: (String) -> Unit,
    onNavigatteToCateGoryAll: () -> Unit,
    onNavigateToProductDetail: (String) -> Unit
) {
    val uiState = remember {
        mutableStateOf(HomeUiState())
    }

    HomeScreenContent(
        uiState = uiState.value,
        onSearchQueryChanged = { uiState.value = uiState.value.copy(searchQuery = it) },
        onCategoryClick = onNavigateToCategory,
        onCategoryAllClick = onNavigatteToCateGoryAll,
        onProductClick = onNavigateToProductDetail,
        onTabSelected = { uiState.value = uiState.value.copy(selectedTab = it) },
        onSearchClick = { }
    )
}

@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onSearchQueryChanged: (String) -> Unit,
    onCategoryClick: (String) -> Unit,
    onCategoryAllClick : () -> Unit,
    onProductClick: (String) -> Unit,
    onTabSelected: (Int) -> Unit,
    onSearchClick: () -> Unit
) {
    // Không dùng Scaffold nữa vì MainScreen đã có rồi
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(8.dp))

        // Search Bar
        SearchBar(
            query = uiState.searchQuery,
            onQueryChange = onSearchQueryChanged,
            onSearchClick = onSearchClick
        )

        Spacer(Modifier.height(16.dp))

        // Banner
        PromotionalBanner()

        Spacer(Modifier.height(24.dp))

        // Categories
        CategoriesSection(
            categories = uiState.categories,
            onCategoryClick = onCategoryClick,
            onCategoryAllClick = onCategoryAllClick,
        )

        Spacer(Modifier.height(24.dp))

        // Tabs
        TabSection(
            selectedTab = uiState.selectedTab,
            onTabSelected = onTabSelected
        )

        // Products Grid
        ProductsGrid(
            products = uiState.products,
            onProductClick = onProductClick
        )

        // Thêm padding dưới cùng để không bị che bởi bottom nav
        Spacer(Modifier.height(90.dp))
    }
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .clickable { onSearchClick() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.Gray,
            modifier = Modifier.size(17.dp)
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = if (query.isEmpty()) "Bạn muốn tìm kiếm gì" else query,
            color = if (query.isEmpty()) Color.Gray else Color.Black,
            fontSize = 14.sp
        )
        Spacer(Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_cart),
            contentDescription = "Cart",
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun PromotionalBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(160.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFE8F5E9))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Giới thiệu bạn mới",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF2E7D32)
                )
                Spacer(Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFF5722))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "NHẬN NGAY VOUCHER",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "20%",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32)
                )
            }
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_banner),
                    contentDescription = "Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
private fun CategoriesSection(
    categories: List<CategoryItem>,
    onCategoryClick: (String) -> Unit,
    onCategoryAllClick: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Danh mục",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Xem tất cả",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.clickable { onCategoryAllClick }
            )
        }
        Spacer(Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->
                CategoryCard(
                    category = category,
                    onClick = { onCategoryClick(category.name) }
                )
            }
        }
    }
}

@Composable
private fun CategoryCard(
    category: CategoryItem,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(80.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF0F0F0))
        ) {
            Image(
                painter = painterResource(id = category.image),
                contentDescription = category.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = category.name,
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun TabSection(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        TabItem(
            text = "Dành cho bạn",
            isSelected = selectedTab == 0,
            onClick = { onTabSelected(0) }
        )
        TabItem(
            text = "Giá tốt",
            isSelected = selectedTab == 1,
            onClick = { onTabSelected(1) }
        )
    }
    Spacer(Modifier.height(16.dp))
}

@Composable
private fun TabItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
        color = if (isSelected) Color(0xFF2E7D32) else Color.Gray,
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 8.dp)
    )
}

@Composable
private fun ProductsGrid(
    products: List<ProductItem>,
    onProductClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products) { product ->
            ProductCard(
                product = product,
                onClick = { onProductClick(product.name) }
            )
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
            .background(Color.White)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(Color(0xFFF5F5F5))
        ) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_heart_outline),
                contentDescription = "Wishlist",
                tint = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .size(20.dp)
                    .align(Alignment.TopEnd)
            )
        }
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = product.name,
                fontSize = 12.sp,
                color = Color.Black,
                maxLines = 2
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = product.price,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE91E63)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreenContent(
        uiState = HomeUiState(),
        onSearchQueryChanged = {},
        onCategoryClick = {},

        onProductClick = {},
        onTabSelected = {},
        onSearchClick = {},
        onCategoryAllClick = {}

    )
}