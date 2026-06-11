package com.uzuu.sobe.feature.main.message

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.uzuu.sobe.domain.model.init.chatList
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(MessageUiState())
    val uiState: StateFlow<MessageUiState> = _uiState.asStateFlow()

    init {
        loadChatList()
    }

    private fun loadChatList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            // Giả lập load data từ API/Database
            try {
                _uiState.update {
                    it.copy(
                        chatList = chatList, // Sử dụng seed data
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun selectChat(chatId: String) {
        _uiState.update { it.copy(selectedChatId = chatId) }
    }

    fun clearSelection() {
        _uiState.update { it.copy(selectedChatId = null) }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}