package com.uzuu.sobe.ui.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    brush: Brush,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = AppTextStyles.Heading3.copy(color = Color.White),
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(22.dp),
    contentPadding: PaddingValues ?= null,
    elevation: Dp = 0.dp,
    border: BorderStroke? = null,
    onClickWithState: ((Boolean) -> Unit)? = null,  // ← Optional: callback với isPressed state
    content: @Composable (() -> Unit)? = null
) {
    // Internal interactionSource - user KHÔNG cần biết
    val interactionSource = remember { MutableInteractionSource() }

    // Đọc trạng thái pressed nếu cần
    val isPressed by interactionSource.collectIsPressedAsState()

    // Default values
    val defaultShape = RoundedCornerShape(28.dp)
    val defaultPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
    val defaultTextStyle = AppTextStyles.Heading3.copy(color = Color.White)

    val finalShape = shape ?: defaultShape
    val finalPadding = contentPadding ?: defaultPadding
    val finalTextStyle = textStyle ?: defaultTextStyle

    // Shadow
    val shadowModifier = if (elevation > 0.dp) {
        Modifier.shadow(elevation, finalShape, clip = false)
    } else Modifier

    Box(
        modifier = modifier
            .then(shadowModifier)
            .then(if (border != null) Modifier.border(border, finalShape) else Modifier)
            .background(
                brush = if (enabled) brush else Brush.linearGradient(
                    colors = listOf(Color.Gray, Color.DarkGray)
                ),
                shape = finalShape
            )
            .clip(finalShape)
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(),
                enabled = enabled,
                onClick = {
                    onClickWithState?.invoke(isPressed)
                    onClick()
                }
            )
            .padding(finalPadding),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = text, style = finalTextStyle)

        if (content != null) {
            content()
        }
    }
}

// Sử dụng:
