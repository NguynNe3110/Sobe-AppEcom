package com.uzuu.sobe.feature.main.community

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
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
import com.uzuu.sobe.domain.model.PostItem
import com.uzuu.sobe.domain.model.init.listPosts
import com.uzuu.sobe.feature.main.account.ProfileScreen
import com.uzuu.sobe.ui.component.PostCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(
    windowSizeClass: WindowSizeClass,
    onPostClick: (PostItem) -> Unit,
    onNavigateToPost: () -> Unit
) {
    // ✅ ĐẨY STATE LÊN ĐẦU (Unidirectional Data Flow)
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Dành cho bạn", "Theo dõi", "Yêu thích", "Đã đăng")
    val posts = listPosts

    // ✅ LOGIC RESPONSIVE
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            CommunityCompactContent(
                tabs = tabs,
                selectedTab = selectedTab,
                posts = posts,
                onTabSelected = { selectedTab = it }, // ✅ Truyền callback
                onPostClick = onPostClick,
                onNavigateToPost = onNavigateToPost
            )
        }
        WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
            CommunityExpandedContent(
                tabs = tabs,
                selectedTab = selectedTab,
                posts = posts,
                onTabSelected = { selectedTab = it }, // ✅ Truyền callback
                onPostClick = onPostClick,
                onNavigateToPost = onNavigateToPost
            )
        }
    }
}

// 🟢 MOBILE: Grid 2 cột
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CommunityCompactContent(
    tabs: List<String>,
    selectedTab: Int,
    posts: List<PostItem>,
    onTabSelected: (Int) -> Unit,
    onPostClick: (PostItem) -> Unit,
    onNavigateToPost: () -> Unit
) {
    CommunityScaffold(
        tabs = tabs,
        selectedTab = selectedTab,
        onTabSelected = onTabSelected,
        onNavigateToPost = onNavigateToPost,
        gridColumns = GridCells.Fixed(2), // Mobile: 2 cột
        posts = posts,
        onPostClick = onPostClick
    )
}

// 🔵 TABLET: Grid Adaptive (Tự chia 3-4 cột)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CommunityExpandedContent(
    tabs: List<String>,
    selectedTab: Int,
    posts: List<PostItem>,
    onTabSelected: (Int) -> Unit,
    onPostClick: (PostItem) -> Unit,
    onNavigateToPost: () -> Unit
) {
    CommunityScaffold(
        tabs = tabs,
        selectedTab = selectedTab,
        onTabSelected = onTabSelected,
        onNavigateToPost = onNavigateToPost,
        gridColumns = GridCells.Adaptive(240.dp), // Tablet: Tự động chia cột rộng 250dp
        posts = posts,
        onPostClick = onPostClick
    )
}

// 🔧 Helper: Dùng chung Scaffold và TopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CommunityScaffold(
    tabs: List<String>,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    onNavigateToPost: () -> Unit,
    gridColumns: GridCells,
    posts: List<PostItem>,
    onPostClick: (PostItem) -> Unit
) {
    Scaffold(
        topBar = {
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
                            painter = painterResource(R.drawable.ic_search_gr),
                            contentDescription = "Search",
                            modifier = Modifier.size(17.dp),
                            tint = Color.Unspecified
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNavigateToPost,
                modifier = Modifier.height(40.dp),
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_add_fab),
                        contentDescription = "Add",
                        modifier = Modifier.size(12.dp),
                        tint = Color.Unspecified
                    )
                },
                text = { Text("Đăng bài") },
                containerColor = AppColor.Secondary.copy(alpha = 0.8f),
                contentColor = Color.White
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            // TabRow (Đã sửa lỗi state)
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.White,
                contentColor = Color.Black,
                divider = {}
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { onTabSelected(index) }, // ✅ Gọi callback
                        text = {
                            Text(
                                text = title,
                                style = AppTextStyles.Heading4,
                                color = if (selectedTab == index) AppColor.Primary else AppColor.textSage100,
                                textDecoration = if (selectedTab == index) TextDecoration.Underline else TextDecoration.None
                            )
                        }
                    )
                }
            }

            // Grid
            LazyVerticalGrid(
                columns = gridColumns, // ✅ Sử dụng tham số truyền vào
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(items = posts, key = { it.id }) { post ->
                    PostCard(post = post, onLikeClick = {}, onPostClick = { onPostClick(post) })
                }
            }
        }
    }
}

// --- PREVIEW ---
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(name = "Phone", device = Devices.PIXEL_5, showBackground = true)
@Composable
fun PreviewCommunityCompact() {
    CommunityScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(393.dp, 851.dp)),
        onPostClick = {},
        onNavigateToPost = {}
    )
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(name = "Tablet", device = Devices.PIXEL_C, showBackground = true)
@Composable
fun PreviewCommunityExpanded() {
    CommunityScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(width = 1280.dp, height = 800.dp) // Kích thước chuẩn cho Expanded
        ),
        onPostClick = {},
        onNavigateToPost = {}
    )
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    name = "Foldable",
    device = Devices.FOLDABLE,
    showBackground = true
)
@Composable
fun PreviewProfileScreen_Foldable() {
    CommunityScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(width = 673.dp, height = 841.dp) // Kích thước chuẩn cho Expanded
        ),
        onPostClick = {},
        onNavigateToPost = {}
    )
}