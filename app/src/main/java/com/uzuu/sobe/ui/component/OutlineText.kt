package com.uzuu.sobe.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush
import com.uzuu.jetpack_compose_hub.feature.home.HomeUiState

@Composable
fun OutlinedBrushText(
    text: String = "Text outline",
    textColor: Color = Color.Black,       // Màu chữ có thể điều chỉnh
    outlineBrush: Brush,                  // Brush cho viền (màu đơn hoặc gradient)
    corner: Dp = 12.dp,
    widthStroke: Dp = 1.dp,
    style: TextStyle = AppTextStyles.Title,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,                 // Sự kiện khi click
) {
    Text(
        text = text,
        color = textColor,
        modifier = modifier
            .clickable { onClick() } // 1. Nhận sự kiện onClick
            .padding(horizontal = 16.dp, vertical = 8.dp) // Padding bên NGOÀI viền
            .border(
                border = BorderStroke(widthStroke, brush = outlineBrush), // 2. Viền sử dụng Brush
                shape = RoundedCornerShape(corner) // Bo góc cho viền (có thể chỉnh thành CircleShape nếu muốn)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp) // Padding bên TRONG viền để chữ không dính sát vào outline
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    OutlinedBrushText(
        text = "Xem tất cả",
        textColor = Color.Black,       // Màu chữ có thể điều chỉnh
        outlineBrush = AppBrush.SageGradient,                  // Brush cho viền (màu đơn hoặc gradient)
        onClick = {},                  // Sự kiện khi click
    )
}