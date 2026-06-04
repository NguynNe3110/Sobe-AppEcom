package com.uzuu.sobe.feature.main.account

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AppColor
import com.uzuu.sobe.ui.component.ItemList
import com.uzuu.sobe.ui.theme.AppDimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSettingsScreen(
    onNavigateBack: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cài đặt tài khoản",
                        color = AppColor.neutral300,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = AppColor.neutral300
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = AppColor.neutral300
                )
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween // Đẩy nội dung lên trên và nút xuống dưới
        ) {
            // Danh sách các cài đặt
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp) // Khoảng cách giữa các item
            ) {
                ItemList(text = "Thông tin tài khoản", onClick = { /* Action */ })
                ItemList(text = "Cài đặt thông báo", onClick = { /* Action */ })
                ItemList(text = "Cài đặt quyền riêng tư", onClick = { /* Action */ })
                ItemList(text = "Điều khoản sử dụng", onClick = { /* Action */ })
                ItemList(text = "Chuyển tài khoản", onClick = { /* Action */ })
            }

            // Nút Đăng xuất ở dưới cùng
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onLogout,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = AppColor.neutral300
                ),
                border = BorderStroke(1.dp, AppColor.neutral400),
                shape = RoundedCornerShape(24.dp) // Bo tròn nhiều cho giống nút pill
            ) {
                Text(
                    text = "Đăng xuất",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Padding dưới cùng
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Pre() {
    AccountSettingsScreen(
        onNavigateBack = {},
        onLogout = {}
    )
}