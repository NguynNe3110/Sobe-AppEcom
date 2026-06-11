package com.uzuu.sobe.feature.main.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush
import com.uzuu.sobe.domain.model.init.chatList
import com.uzuu.sobe.ui.component.ChatListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageScreen(windowSizeClass: WindowSizeClass) {
    // ✅ LOGIC RESPONSIVE
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MessageCompactContent()
        }
        WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
            // Tablet: Master-Detail Layout
            MessageExpandedContent()
        }
    }
}

// 🟢 MOBILE: List toàn màn hình
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MessageCompactContent() {
    Scaffold(
        topBar = { MessageTopAppBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            items(items = chatList, key = { it.id }) { chatItem ->
                ChatListItem(chatItem = chatItem)
            }
        }
    }
}

// 🔵 TABLET: Master-Detail (Danh sách bên trái, Chi tiết bên phải)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MessageExpandedContent() {
    Scaffold(
        topBar = { MessageTopAppBar() }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Pane 1: Danh sách tin nhắn (Chiếm 1 phần)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.White)
            ) {
                LazyColumn {
                    items(items = chatList, key = { it.id }) { chatItem ->
                        ChatListItem(chatItem = chatItem)
                    }
                }
            }

            // Pane 2: Nội dung chi tiết (Chiếm 2 phần)
            Box(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .background(Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Chọn một cuộc trò chuyện để bắt đầu",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Gray
                )
            }
        }
    }
}

// 🔧 Helper: Dùng chung TopAppBar để tránh lặp code
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MessageTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Tin nhắn",
                style = AppTextStyles.Heading1 + TextStyle(AppBrush.SageGradient),
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}

// --- PREVIEW ---
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(name = "Phone", device = Devices.PIXEL_5, showBackground = true)
@Composable
fun PreviewMessageCompact() {
    MessageScreen(windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(393.dp, 851.dp)))
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(name = "Tablet", device = Devices.PIXEL_C, showBackground = true)
@Composable
fun PreviewMessageExpanded() {
    MessageScreen(windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(900.dp, 1280.dp)))
}