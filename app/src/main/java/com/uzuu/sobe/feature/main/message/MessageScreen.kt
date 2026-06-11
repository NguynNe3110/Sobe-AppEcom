package com.uzuu.sobe.feature.main.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush
import com.uzuu.jetpack_compose_hub.feature.learn6_navigation.feature.auth.login.RegisterScreenContent
import com.uzuu.sobe.domain.model.init.chatList
import com.uzuu.sobe.ui.component.ChatListItem
import com.uzuu.sobe.R
import com.uzuu.sobe.feature.auth.login.LoginViewModel
import com.uzuu.sobe.feature.main.message.MessageViewModel
import com.uzuu.sobe.feature.main.shopping.ShoppingScreen


@Composable
fun MessageScreen(
    viewModel: MessageViewModel = hiltViewModel(),
    onNavigateToRegister: () -> Unit,
    onNavigateToForgetPassword: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    // 1. Thu thập state từ ViewModel
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // khoi tạo collect = compose, sau dó gọi hàm. giống bên register
    MessageScreenContent(
        uiState = uiState,
    )
}
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MessageScreenContent(
    windowSizeClass: WindowSizeClass,

) {
    val selectedChatId by viewModel.selectedChatId.collectAsStateWithLifecycle()

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (selectedChatId == null) {
                MessageCompactContent(onChatClick = viewModel::selectChat)
            } else {
                // Truyền thêm onClose để xử lý nút Back trên Phone
                ChatDetailPane(
                    chatId = selectedChatId!!,
                    onClose = viewModel::clearSelection
                )
            }
        }
        WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
            MessageExpandedContent(
                selectedChatId = selectedChatId,
                onChatClick = viewModel::selectChat
            )
        }
    }
}

// --- COMPACT CONTENT (PHONE) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MessageCompactContent(onChatClick: (String) -> Unit) {
    Scaffold(topBar = { MessageTopAppBar() }) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(Color.White)
        ) {
            items(items = chatList, key = { it.id }) { chatItem ->
                ChatListItem(
                    chatItem = chatItem,
                    onClick = { onChatClick(chatItem.id) }
                )
            }
        }
    }
}

// --- EXPANDED CONTENT (TABLET/FOLDABLE) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MessageExpandedContent(
    selectedChatId: String?,
    onChatClick: (String) -> Unit
) {
    Scaffold(topBar = { MessageTopAppBar() }) { paddingValues ->
        Row(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            // Pane 1: Master List
            Box(
                modifier = Modifier.weight(1f).fillMaxHeight().background(Color.White)
            ) {
                LazyColumn {
                    items(items = chatList, key = { it.id }) { chatItem ->
                        ChatListItem(
                            chatItem = chatItem,
                            onClick = { onChatClick(chatItem.id) },
                            isSelected = chatItem.id == selectedChatId
                        )
                    }
                }
            }

            // Pane 2: Detail View
            ChatDetailPane(
                chatId = selectedChatId,
                modifier = Modifier.weight(2f)
            )
        }
    }
}

// --- CHAT DETAIL PANE (WRAPPER) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetailPane(
    chatId: String?,
    onClose: (() -> Unit)? = null, // Nullable vì Tablet không cần nút back
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center
    ) {
        if (chatId == null) {
            Text(
                text = "Chọn một cuộc trò chuyện để bắt đầu",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Gray
            )
        } else {
            // Nếu là Phone (có onClose), ta có thể bọc ChatScreen bằng Scaffold có TopBar chứa nút Back
            if (onClose != null) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Tin nhắn") },
                            navigationIcon = {
                                IconButton(onClick = onClose) {
                                    Icon(
                                        modifier = Modifier
                                            .padding(start = 16.dp) // Padding trái thay vì all
                                            .size(24.dp),           // Size icon chuẩn Material
                                        painter = painterResource(id = R.drawable.ic_arrow_left),
                                        contentDescription = "Back",
                                        tint = Color.Unspecified // thuộc tính này cho phép loại bỏ lớp phủ mawjc điịnh của gg
                                    )
                                }
                            }
                        )
                    }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        ChatScreen(chatId = chatId)
                    }
                }
            } else {
                // Tablet: Hiển thị trực tiếp không cần header riêng
                ChatScreen(chatId = chatId)
            }
        }
    }
}

// Helper TopAppBar giữ nguyên
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
@Preview(name = "Phone - Portrait", device = Devices.PIXEL_5, showBackground = true)
@Composable
fun PreviewShoppingScreen_Phone() {
    MessageScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(393.dp, 851.dp)),
        viewModel = MessageViewModel = hiltViewModel()
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(name = "Tablet - Landscape", device = Devices.PIXEL_C, showBackground = true)
@Composable
fun PreviewShoppingScreen_Tablet() {
    MessageScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(width = 1280.dp, height = 800.dp)),
        onProductClick = {}
    )
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(name = "Foldable", device = Devices.FOLDABLE, showBackground = true)
@Composable
fun PreviewShoppingScreen_Foldable() {
    MessageScreen(
        windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(673.dp, 841.dp)),
        onProductClick = {}
    )
}