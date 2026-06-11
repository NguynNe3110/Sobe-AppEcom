package com.uzuu.sobe.feature.main.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uzuu.sobe.R

// --- DATA CLASS ---
data class Message(
    val id: String,
    val content: String,
    val isSender: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

data class ChatUiState(
    val messages: List<Message> = emptyList(),
    val inputText: String = ""
)

// --- MAIN SCREEN ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    chatId: String,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberLazyListState()

    Scaffold(
        bottomBar = {
            InputBar(
                text = uiState.inputText,
                onTextChanged = viewModel::onInputChanged,
                onSend = viewModel::sendMessage
            )
        },
        containerColor = Color.Transparent
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                state = scrollState,
                reverseLayout = true,
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Bottom)
            ) {
                items(
                    items = uiState.messages,
                    key = { it.id }
                ) { message ->
                    val currentIndex = uiState.messages.indexOf(message)
                    val previousMessage =
                        if (currentIndex > 0) uiState.messages[currentIndex - 1] else null
                    val nextMessage =
                        if (currentIndex < uiState.messages.size - 1)
                            uiState.messages[currentIndex + 1]
                        else null

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = if (message.isSender)
                            Arrangement.End else Arrangement.Start
                    ) {
                        MessageBubble(
                            message = message,
                            previousMessage = previousMessage,
                            nextMessage = nextMessage
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(uiState.messages.size) {
        if (uiState.messages.isNotEmpty()) {
            scrollState.animateScrollToItem(0)
        }
    }
}

// --- SPEECH BUBBLE COMPONENT ---
@Composable
fun MessageBubble(
    message: Message,
    previousMessage: Message? = null,
    nextMessage: Message? = null
) {
    val isSender = message.isSender
    val backgroundColor = if (isSender)
        Color(0xFF5B8C5A) else Color(0xFF7FA87D) // Màu xanh đậm/nhạt như ảnh

    // Xác định corner radius dựa trên vị trí tin nhắn
    val cornerShape = determineBubbleShape(
        isSender = isSender,
        isTopOfGroup = previousMessage?.isSender != isSender,
        isBottomOfGroup = nextMessage?.isSender != isSender
    )

    Surface(
        modifier = Modifier.widthIn(max = 260.dp),
        color = backgroundColor,
        shape = cornerShape
    ) {
        Text(
            text = message.content,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}

// Helper function để xác định shape của bubble
@Composable
private fun determineBubbleShape(
    isSender: Boolean,
    isTopOfGroup: Boolean,
    isBottomOfGroup: Boolean
): RoundedCornerShape {
    return when {
        // Tin nhắn đơn lẻ (không có ai nhắn trước/sau cùng người)
        isTopOfGroup && isBottomOfGroup -> {
            if (isSender) {
                // Bên phải: bo tất cả trừ top-left
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 4.dp,  // Góc nhỏ như ảnh
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            } else {
                // Bên trái: bo tất cả trừ top-right
                RoundedCornerShape(
                    topStart = 4.dp,  // Góc nhỏ như ảnh
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            }
        }
        // Tin nhắn đầu tiên trong nhóm
        isTopOfGroup -> {
            if (isSender) {
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 4.dp,
                    bottomStart = 4.dp,   // Không bo vì nối với tin dưới
                    bottomEnd = 4.dp
                )
            } else {
                RoundedCornerShape(
                    topStart = 4.dp,
                    topEnd = 16.dp,
                    bottomStart = 4.dp,
                    bottomEnd = 4.dp
                )
            }
        }
        // Tin nhắn cuối cùng trong nhóm
        isBottomOfGroup -> {
            if (isSender) {
                RoundedCornerShape(
                    topStart = 4.dp,
                    topEnd = 4.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            } else {
                RoundedCornerShape(
                    topStart = 4.dp,
                    topEnd = 4.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            }
        }
        // Tin nhắn ở giữa nhóm
        else -> {
            RoundedCornerShape(4.dp) // Bo nhỏ tất cả các góc
        }
    }
}

// --- INPUT BAR COMPONENT (Giống ảnh mẫu) ---
@Composable
fun InputBar(
    text: String,
    onTextChanged: (String) -> Unit,
    onSend: () -> Unit
) {
    Surface(tonalElevation = 4.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = onTextChanged,
                modifier = Modifier.weight(1f),
                placeholder = { Text("Nhập tin nhắn...") },
                shape = RoundedCornerShape(24.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5)
                )
            )

            IconButton(onClick = onSend) {
                Icon(
                    modifier = Modifier
                        .padding(start = 16.dp) // Padding trái thay vì all
                        .size(24.dp),           // Size icon chuẩn Material
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = "Back",
                    tint = Color.Unspecified // thuộc tính này cho phép loại bỏ lớp phủ mawjc điịnh của gg
                )
            }
        }
    }
}