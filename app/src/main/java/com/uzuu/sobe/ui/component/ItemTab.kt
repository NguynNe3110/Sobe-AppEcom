package com.uzuu.sobe.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppColor

@Composable
fun TabItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = text,
        color = if (isSelected) AppColor.Primary else AppColor.neutral300,
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        style = AppTextStyles.Heading3,
        textDecoration = if (isSelected) TextDecoration.Underline else TextDecoration.None
    )
}