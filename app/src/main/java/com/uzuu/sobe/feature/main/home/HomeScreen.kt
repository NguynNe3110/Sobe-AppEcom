package com.uzuu.jetpack_compose_hub.feature.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sobe.ui.theme.AppTextStyles
import com.example.sobe.ui.theme.ProstoOneFontFamily
import com.example.ui.theme.AppBrush
import com.example.ui.theme.AppColor
import com.uzuu.sobe.R
import com.uzuu.sobe.domain.model.CategoryItem
import com.uzuu.sobe.domain.model.ProductItem
import com.uzuu.sobe.domain.model.init.listBanners
import com.uzuu.sobe.domain.model.init.listCategories
import com.uzuu.sobe.domain.model.init.listProducts
import com.uzuu.sobe.ui.component.OutlinedBrushText
import com.uzuu.sobe.ui.component.ProductCard
import com.uzuu.sobe.ui.component.TabItem
import com.uzuu.sobe.ui.theme.AppDimens

// Data classes
data class HomeUiState(
    val searchQuery: String = "",
    val categories: List<CategoryItem> = listCategories,
    val products: List<ProductItem> = listProducts,
    val selectedTab: Int = 0
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
    // ✅ SỬA LỖI: Dùng 1 LazyVerticalGrid duy nhất cho toàn màn hình
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 16.dp), // Padding chung cho toàn bộ lưới
        contentPadding = PaddingValues(bottom = 90.dp, top = 8.dp), // Chừa đáy cho BottomNav
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // ✅ Search Bar: Chiếm FULL chiều rộng của Grid
        item(span = { GridItemSpan(maxLineSpan) }) {
            SearchBar(
                keyword = uiState.searchQuery,
                onKeywordChange = onSearchQueryChanged,
            )
        }

        // ✅ Banner: Chiếm FULL chiều rộng
        item(span = { GridItemSpan(maxLineSpan) }) {
            PromotionalBanner()
        }

        // ✅ Categories: Chiếm FULL chiều rộng
        item(span = { GridItemSpan(maxLineSpan) }) {
            CategoriesSection(
                categories = uiState.categories,
                onCategoryClick = onCategoryClick,
                onCategoryAllClick = onCategoryAllClick,
            )
        }

        // ✅ Tabs: Chiếm FULL chiều rộng
        item(span = { GridItemSpan(maxLineSpan) }) {
            TabSection(
                selectedTab = uiState.selectedTab,
                onTabSelected = onTabSelected
            )
        }

        // ✅ Products: Tự động chia cột, không cần hàm ProductsGrid riêng nữa
        items(uiState.products) { product ->
            ProductCard(
                product = product,
                onClick = { onProductClick(product.name) }
            )
        }
    }
}

@Composable
fun SearchBar(
    keyword: String,
    onKeywordChange: (String) -> Unit,
//    modifier: Modifier = Modifier
) {
    //responsive mức 2 (có thẻ dung  windowsizeClass)
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val currentMaxWidth = maxWidth

        Row(
            modifier = Modifier.fillMaxWidth(),
//            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            if(currentMaxWidth > 600.dp) {
                Icon(
                    modifier = Modifier
                        .size(45.dp),
                    painter = painterResource(R.drawable.logo_sobe),
                    contentDescription = null,
                    tint = AppColor.Primary
                )

                Spacer(Modifier.width(12.dp))

                Text(
                    text = "SOBE",
                    color = AppColor.Primary,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        fontFamily = ProstoOneFontFamily
                    )
                )

                Spacer(Modifier.width(12.dp))

            } else {

            }

            OutlinedTextField(
                value = keyword,
                onValueChange = onKeywordChange,

                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),

                singleLine = true,

                placeholder = {
                    Text("Bạn muốn tìm kiếm gì")
                },

                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = null
                    )
                },

                trailingIcon = {
                    IconButton(
                        onClick = { onKeywordChange("") }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_cancel),
                            contentDescription = null
                        )
                    }
                },

                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,

                    // Màu viền
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = AppColor.Primary,      // Viền khi focus
                    unfocusedIndicatorColor = AppColor.neutral300,  // Viền bình thường
                    disabledIndicatorColor = AppColor.neutral300,

                    // Màu Label
                    focusedLabelColor = AppColor.Primary,
                    unfocusedLabelColor = AppColor.neutral500,

                    // Màu con trỏ
                    cursorColor = AppColor.Primary
                )
            )

            Spacer(Modifier.width(12.dp))

            Icon(
                painter = painterResource(R.drawable.ic_cart),
                contentDescription = "Cart",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun PromotionalBanner() {

    val banners = listBanners

    val pagerState = rememberPagerState(
        pageCount = { banners.size }
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
                .height(160.dp)
        ) { page ->

            Image(
                painter = painterResource(banners[page]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(AppDimens.Corner.cornerRadiusTwenty)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(banners.size) { index ->

                val color =
                    if (pagerState.currentPage == index)
                        Color(0xFF4CAF50)
                    else
                        Color.LightGray

                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(color)
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
//        modifier = Modifier.padding(horizontal = 16.dp)
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
            OutlinedBrushText(
                text = "Xem tất cả",
                textColor = AppColor.Primary,
                onClick = { },
                outlineBrush = AppBrush.SageGradient,
                corner = AppDimens.Corner.cornerRadiusLarge,
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
//
//@Composable
//private fun CategoryCard(
//    category: CategoryItem,
//    onClick: () -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .width(80.dp)
//            .clickable { onClick() },
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Box(
//            modifier = Modifier
//                .size(80.dp)
//                .clip(RoundedCornerShape(16.dp))
//                .background(Color(0xFFF0F0F0))
//        ) {
//            Image(
//                painter = painterResource(id = category.image),
//                contentDescription = category.name,
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.Crop
//            )
//        }
//        Spacer(Modifier.height(8.dp))
//        Text(
//            text = category.name,
//            fontSize = 14.sp,
//            color = Color.Black,
//            textAlign = TextAlign.Center
//        )
//    }
//}
//
//@Composable
//fun CategoryCard(
//    category : CategoryItem,
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier
//            .height(150.dp)
//            .clickable(onClick = onClick),
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Box {
//            // Background Image
//            Image(
//                painter = painterResource(id = category.image),
//                contentDescription = category.name,
//                modifier = Modifier.fillMaxSize()
//                    .height(64.dp),
//                contentScale = ContentScale.Crop
//            )
//
//            // Dark overlay
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Black.copy(alpha = 0.3f))
//            )
//
//            // Text
//            Text(
//                text = category.name,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.White,
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
//    }
//}
@Composable
fun CategoryCard(
    category : CategoryItem,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(90.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {

        Image(
            painter = painterResource(category.image),
            contentDescription = category.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Lớp làm mờ tối phía dưới
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.55f)
                        )
                    )
                )
        )

        Text(
            text = category.name,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
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
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TabItem(
            text = "Dành cho bạn",
            isSelected = selectedTab == 0,
            onClick = { onTabSelected(0) }
        )
        Spacer(Modifier.width(AppDimens.Spacing.spacingLarge))

        TabItem(
            text = "Giá tốt",
            isSelected = selectedTab == 1,
            onClick = { onTabSelected(1) }
        )
    }
    Spacer(Modifier.height(16.dp))
}



@Composable
private fun ProductsGrid(
    products: List<ProductItem>,
    onProductClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 140.dp),
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

@Preview(showBackground = true)
@Preview(
    name = "Phone - Portrait",
    device = Devices.PIXEL_5
)
@Preview(
    name = "Tablet - Landscape",
    device = Devices.PIXEL_C
)
@Preview(
    name = "Foldable",
    device = Devices.FOLDABLE
)
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