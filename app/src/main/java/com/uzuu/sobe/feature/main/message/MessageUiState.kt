package com.uzuu.sobe.feature.main.message

import com.uzuu.sobe.domain.model.ChatItem

data class MessageUiState(
    val chatList: List<ChatItem> = emptyList(),
    val selectedChatId: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)