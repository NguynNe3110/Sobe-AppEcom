package com.uzuu.sobe.feature.main.community

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush
import com.example.ui.theme.AppColor
import com.uzuu.sobe.R
import com.uzuu.sobe.domain.model.CategoryItem
import com.uzuu.sobe.domain.model.PostItem
import com.uzuu.sobe.domain.model.ProductItem
import com.uzuu.sobe.domain.model.init.listBanners
import com.uzuu.sobe.domain.model.init.listCategories
import com.uzuu.sobe.domain.model.init.listPosts
import com.uzuu.sobe.domain.model.init.listProducts
import com.uzuu.sobe.ui.component.OutlinedBrushText
import com.uzuu.sobe.ui.component.PostCard
import com.uzuu.sobe.ui.component.ProductCard
import com.uzuu.sobe.ui.theme.AppDimens
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(
    onPostClick: (PostItem) -> Unit,
    onNavigateToPost: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Dành cho bạn", "Theo dõi", "Yêu thích", "Đã đăng")
    val posts = listPosts

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cộng đồng",
                        style = AppTextStyles.Heading1 + TextStyle(AppBrush.SageGradient),
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = { /* Search */ }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "Cart",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNavigateToPost,
                icon = {
                    Icon(
                    painter = painterResource(R.drawable.ic_add_fab),
                    contentDescription = "Cart",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                    )
                },
                text = { Text("Đăng bài") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 6.dp
                )
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Tab Layout
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.White,
                contentColor = Color.Black,
                divider = {}
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
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Color.Gray
                    )
                }
            }

            // Posts Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = posts,
                    key = { it.id }
                ) { post ->
                    PostCard(
                        post = post,
                        onLikeClick = { /* Handle like */ },
                        onPostClick = { onPostClick(post) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun Pre() {
    CommunityScreen(
        onPostClick = {},
        onNavigateToPost = {}
    )
}