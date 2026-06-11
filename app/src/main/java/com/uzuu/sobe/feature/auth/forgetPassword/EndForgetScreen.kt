package com.uzuu.sobe.feature.auth.forgetPassword

import com.uzuu.sobe.feature.auth.welcome.WelcomeViewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush
import com.example.ui.theme.AppColor
import com.uzuu.sobe.R
import com.uzuu.sobe.ui.component.button.GradientButton
import com.uzuu.sobe.ui.theme.AppDimens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun EndForgetScreen(
    onNavigationToHome: () -> Unit
) {
    EndForgetScreenContent(
        onNavigationToHome = onNavigationToHome
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EndForgetScreenContent(
    onNavigationToHome: () -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Illustration Image
            Image(
                painter = painterResource(id = R.drawable.img_end_register),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = ("THÀNH CÔNG"),
                    fontSize = 24.sp,
                    style = TextStyle(AppBrush.SageGradient) + AppTextStyles.Heading1,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Description
                Text(
                    text = ("Mật khẩu đã được cập nhật. SOBE đang chờ bạn cùng tiếp tục hành trình sống xanh"),
                    fontSize = 14.sp,
                    color = AppColor.neutral300,
                    textAlign = TextAlign.Center,
                    style = TextStyle(AppBrush.SageGradient),
                    lineHeight = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

            Column(
                modifier = Modifier.padding(AppDimens.Padding.paddingExtraLarge)
                    .fillMaxWidth()
            ) {
                GradientButton(
                    text = ("Đăng nhập"),
                    onClick = {
                        // Xử lý logic đăng nhập ở đây
                        onNavigationToHome()
                    },
                    brush = AppBrush.SageGradient,
                    modifier = Modifier
                        .fillMaxWidth()       // Nút trải dài theo chiều ngang
                        .height(45.dp),       // Chiều cao tùy chỉnh cho cân đối
                    textStyle = AppTextStyles.Heading3.copy(
                        color = Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 24.dp)
                ) {
                    // Nội dung bên trong Box: Text + Icon
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_enter), // Hoặc ic_arrow_enter của bạn
                            contentDescription = "enter home",
                            tint = Color.White,
                            modifier = Modifier.size(AppDimens.IconSize.IconSmall)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EndForgetScreenPreview() {
    EndForgetScreenContent(
        onNavigationToHome = {}
    )
}


