package com.uzuu.sobe.domain.model

data class PostItem(
    val id: String,
    val title: String,
    val image: Int,              // Drawable resource ID
    val authorName: String,
    val authorAvatar: Int,       // Drawable resource ID
    val likesCount: Int,
    val isLiked: Boolean = false,
    val imageCount: Int = 1
)