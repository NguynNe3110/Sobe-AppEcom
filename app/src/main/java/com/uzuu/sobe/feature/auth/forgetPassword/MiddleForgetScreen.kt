package com.uzuu.sobe.feature.auth.forgetPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush
import com.example.ui.theme.AppColor
import com.uzuu.sobe.R
import com.uzuu.sobe.ui.component.button.GradientButton
import com.uzuu.sobe.ui.theme.AppDimens

@Composable
fun MiddleForgetScreen(
    onClickConfirm: () -> Unit ,
    onRequestAgain: () -> Unit,
    onBackClick: () -> Unit,
) {
    MiddleForgetScreenContent(
        onClickConfirm = onClickConfirm,
        onRequestAgain = onRequestAgain,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiddleForgetScreenContent(
    otpCode: String = "", // Nhận mã OTP từ bên ngoài
    isTimerActive: Boolean = true, // Trạng thái đếm ngược
    timeRemaining: String = "01:52", // Thời gian còn lại
    onClickConfirm: () -> Unit ,
    onRequestAgain: () -> Unit,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(start = AppDimens.Padding.paddingMedium, top = AppDimens.Padding.paddingMedium)
                            .size(AppDimens.IconSize.IconSmall),
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "Back",
                        tint = Color.Unspecified
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = AppDimens.Spacing.spacingExtraLarge)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppDimens.Spacing.spacingExtraLarge)
        ) {
            Spacer(modifier = Modifier.height(AppDimens.Spacing.spacingExtraLarge))

            // Phần tiêu đề
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(AppDimens.Spacing.spacingExtraSmall)
            ) {
                Text(
                    text = "QUÊN MẬT KHẨU",
                    color = AppColor.Primary,
                    style = AppTextStyles.Heading1
                )
                Text(
                    text = "Mã xác nhận đã được gửi tới số điện thoại của bạn. \n" +
                            "Vui lòng kiểm tra và điền chính xác mã xác nhận.",
                    color = AppColor.neutral500,
                    style = AppTextStyles.SupportText,
                    textAlign = TextAlign.Center
                )
            }

            // Phần nhập OTP
            OtpInputSection(otpCode = otpCode)

            // Phần đếm ngược
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Mã còn hiệu lực trong ", style = AppTextStyles.SupportText, color = AppColor.neutral600)
                Text(timeRemaining, style = AppTextStyles.Label, color = AppColor.neutral300)
            }

            // Phần nút bấm
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                GradientButton(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    text = "Xác nhận",
                    brush = AppBrush.SageGradient,
                    onClick = onClickConfirm,
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    shape = RoundedCornerShape(25.dp),
                    textStyle = AppTextStyles.Heading3.copy(color = Color.White),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Bạn đã nhận được mã?", style = AppTextStyles.Title, color = AppColor.black)
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = onRequestAgain, enabled = !isTimerActive) {
                        Text(
                            "Gửi lại mã",
                            fontSize = 14.sp,
                            style = AppTextStyles.Heading4,
                            color = AppColor.Primary,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun OtpInputSection(otpCode: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(AppDimens.Spacing.spacingTwenty),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(4) { index ->
            val char = otpCode.getOrNull(index)?.toString() ?: ""

            BasicTextField(
                value = char,
                onValueChange = { }, // Logic xử lý nhập sẽ do ViewModel hoặc Parent quản lý
                enabled = false, // Tạm thời disable input trực tiếp ở đây nếu muốn quản lý tập trung
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier
                    .size(48.dp)
                    .border(
                        width = 1.dp,
                        color = if (char.isNotEmpty()) Color(0xFF4A6C58) else Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(Color.White, RoundedCornerShape(8.dp)),
                decorationBox = { innerTextField ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        innerTextField()
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMiddleForgetScreen() {
    MiddleForgetScreenContent(
        otpCode = "12",
        isTimerActive = true,
        timeRemaining = "01:52",
        onClickConfirm = {},
        onRequestAgain = {},
        onBackClick = {}
    )
}