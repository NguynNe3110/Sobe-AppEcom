package com.uzuu.sobe.feature.auth.forgetPassword

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush
import com.example.ui.theme.AppColor
import com.uzuu.sobe.R
import com.uzuu.sobe.ui.component.button.GradientButton
import com.uzuu.sobe.ui.theme.AppDimens


@Composable
fun StartForgetScreen(
    onNavigateToOTPScreen: () -> Unit
) {
    RegisterScreenContent(
//        uiState = uiState,
//        onPhoneChanged = viewModel::onPhoneChanged,
//        onPasswordChanged = viewModel::onPasswordChanged,
//
//        onGoogleLogin = viewModel::onGoogleLogin,
//        onFacebookLogin = viewModel::onFacebookLogin,
//
//        onNavigateToRegister = viewModel::onNavigateToRegister,
//        onNavigateToHome = viewModel::onNavigateToHome,
        onNavigateToOTPScreen = onNavigateToOTPScreen
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenContent(
    onNavigateToOTPScreen: () -> Unit
) {
    var numberPhone by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(
                                start = AppDimens.Padding.paddingMedium,
                                top = AppDimens.Padding.paddingMedium
                            ) // Padding trái thay vì all
                            .size(AppDimens.IconSize.IconSmall),           // Size icon chuẩn Material
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "Back",
                        tint = Color.Unspecified
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {},

        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppDimens.Spacing.spacingExtraLarge)
        ) {
            Spacer(Modifier.height(24.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "QUÊN MẬT KHẨU",
                    color = AppColor.Primary,
                    style = AppTextStyles.Heading1,
                )
                Text(
                    text = "Vui lòng nhập số điện thoại bạn đã đăng ký tài khoản",
                    color = AppColor.neutral500,
                    style = AppTextStyles.SupportText,
                    modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
                )
            }

            // Input Số điện thoại
            OutlinedTextField(
                value = numberPhone, // Lấy giá trị từ state
                onValueChange = { numberPhone = it },
                label = { Text("Số điện thoại") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp, max = 56.dp),
                shape = MaterialTheme.shapes.extraLarge,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,

                    // Màu viền
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = AppColor.Primary,      // Viền khi focus
                    unfocusedIndicatorColor = AppColor.neutral300,  // Viền bình thường
                    disabledIndicatorColor = AppColor.neutral300,

                    // Màu Label
                    focusedLabelColor = AppColor.Primary,
                    unfocusedLabelColor = AppColor.neutral500,

                    // Màu con trỏ
                    cursorColor = AppColor.Primary
                )
            )


            // Nút Đăng ký chính
            GradientButton(
                text = "Đặt lại mật khẩu",
                onClick = onNavigateToOTPScreen,
                brush = AppBrush.SageGradient,
//                enabled = !uiState.isLoading,
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 24.dp),
                textStyle = AppTextStyles.Heading3.copy(color = Color.White)
            ) {
//                if (uiState.isLoading) {
//                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
//                } else {
//                    Text("Đăng ký", fontSize = 16.sp, fontWeight = FontWeight.Medium)
//                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewRegisterScreen() {
    // Giả lập trạng thái UI để xem trước giao diện
    RegisterScreenContent(
        onNavigateToOTPScreen = {},
    )
}