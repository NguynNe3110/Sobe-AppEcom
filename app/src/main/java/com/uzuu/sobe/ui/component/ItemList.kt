package com.uzuu.sobe.ui.component

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.theme.AppColor
import com.uzuu.sobe.R
import com.uzuu.sobe.ui.theme.AppDimens

@Composable
fun ItemList(
    text: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppDimens.Corner.cornerRadiusLarge))
            .background(AppColor.neutral1000) // màu nền xám nhạt
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp)
            .height(40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = AppColor.neutral300,
            fontWeight = FontWeight.Normal
        )

        Icon(
            painter = painterResource(R.drawable.ic_arrow_inlist),
            contentDescription = "Navigate",
            tint = AppColor.neutral300,
            modifier = Modifier.width(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Pre() {
    ItemList(
        text = "Thong tin tai khoan",
        onClick = {}
    )
}