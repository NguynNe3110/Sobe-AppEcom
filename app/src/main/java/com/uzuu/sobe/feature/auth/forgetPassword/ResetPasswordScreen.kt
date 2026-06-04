package com.uzuu.sobe.feature.auth.forgetPassword

import com.uzuu.sobe.feature.auth.register.RegisterUiState
import com.uzuu.sobe.feature.auth.register.RegisterViewModel

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush
import com.example.ui.theme.AppColor
import com.uzuu.sobe.R
import com.uzuu.sobe.ui.component.button.GradientButton
import com.uzuu.sobe.ui.theme.AppDimens


@Composable
fun ResetPasswordScreen(
//    viewModel: RegisterViewModel = hiltViewModel(), // Khởi tạo mặc định
    onClickConfirmToEnd: () -> Unit,
) {
    // 1. Thu thập state từ ViewModel
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ResetPasswordScreenContent(

        onClickConfirmToEnd = onClickConfirmToEnd
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreenContent(
//    uiState: RegisterUiState, // Nhận state từ bên ngoài
//    onPhoneChanged: (String) -> Unit,
//    onPasswordChanged: (String) -> Unit,
//    onAgreementChanged: (Boolean) -> Unit,
//    onRegisterClick: () -> Unit,
//    onRegisterGoogle: () -> Unit,
//    onRegisterFacebook: () -> Unit,
    onClickConfirmToEnd: () -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    var newpass by remember { mutableStateOf("") }
    var confirmNewpass by remember { mutableStateOf("") }

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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(AppDimens.Spacing.spacingExtraLarge))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "ĐẶT LẠI MẬT KHẨU",
                    color = AppColor.Primary,
                    style = TextStyle(AppBrush.SageGradient) + AppTextStyles.Heading1,
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Vui lòng nhập  mật khẩu mới khác với" ,
                    color = AppColor.neutral500,
                    style = AppTextStyles.SupportText,
                )
                Text(
                    text = "mật khẩu đã tạo trước đây.",
                    color = AppColor.neutral500,
                    style = AppTextStyles.SupportText,
                    modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
                )
            }

            // Input Số điện thoại
            OutlinedTextField(
                value = newpass, // Lấy giá trị từ state
                onValueChange = { }, // Gọi hàm từ VM
                label = { Text("Mật khẩu mới") },
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

            Spacer(Modifier.height(16.dp))

            // Input Mật khẩu
            OutlinedTextField(
                value = confirmNewpass,
                onValueChange = {  },
                label = { Text("Mật khẩu" ) },
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        // Dùng icon vector thay vì emoji cho chuyên nghiệp hơn
                        Icon(
                            painter = painterResource(
                                id = if (isPasswordVisible) R.drawable.ic_eye_off else R.drawable.ic_eye_on
                            ),
                            contentDescription = if (isPasswordVisible) "Ẩn mật khẩu" else "Hiện mật khẩu"
                        )
                    }
                },
                visualTransformation = if (isPasswordVisible)
                    VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp, max = 56.dp), // ← Fix cứng cả min lẫn max
                shape = MaterialTheme.shapes.extraLarge
            )

            Spacer(Modifier.height(24.dp))

            // Nút Đăng ký chính
            GradientButton(
                text = "Xác nhận",
                onClick = onClickConfirmToEnd,
                brush = AppBrush.SageGradient,
//                enabled = !uiState.isLoading,
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 24.dp),
                textStyle = AppTextStyles.Heading3.copy(color = Color.White)
            )
        }
    }
}

@Preview
@Composable
fun PreviewResetPasswordScreen() {
    // Giả lập trạng thái UI để xem trước giao diện
    ResetPasswordScreenContent(
        onClickConfirmToEnd = {},
    )
}