package com.uzuu.sobe.domain.model

data class ChatItem(
    val id: String,
    val userName: String,
    val userAvatar: Int,
    val message: String,
    val time: String,
    val isReplied: Boolean
)