package com.uzuu.sobe.feature.main.message

import androidx.lifecycle.ViewModel
import com.uzuu.sobe.domain.model.init.textChatList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        // Load seed data khi màn hình mở lần đầu
        // Lưu ý: Vì LazyColumn đang reverseLayout=true,
        // nên ta giữ nguyên thứ tự thời gian (cũ -> mới)
        // Compose sẽ tự đảo ngược để hiển thị đúng
        _uiState.update { it.copy(messages = textChatList) }
    }

    fun onInputChanged(newText: String) {
        _uiState.update { it.copy(inputText = newText) }
    }

    fun sendMessage() {
        val currentText = _uiState.value.inputText.trim()
        if (currentText.isEmpty()) return

        val newMessage = Message(
            id = System.currentTimeMillis().toString(),
            content = currentText,
            isSender = true
        )

        // Thêm vào ĐẦU list vì đang dùng reverseLayout
        _uiState.update {
            it.copy(
                messages = listOf(newMessage) + it.messages,
                inputText = ""
            )
        }
    }
}