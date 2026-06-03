package com.uzuu.sobe.ui.theme

// Shapes.kt
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    // Dùng cho Input, Button, Card nhỏ
    small = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(22.dp),

    // Dùng cho Dialog, BottomSheet
    extraLarge = RoundedCornerShape(32.dp),
    // Giữ nguyên mặc định hoặc tùy chỉnh
    medium = RoundedCornerShape(12.dp)

)