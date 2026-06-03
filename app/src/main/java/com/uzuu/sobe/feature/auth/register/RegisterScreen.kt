package com.uzuu.sobe.feature.auth.register

import android.R.attr.password
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.painterResource
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
import java.time.format.TextStyle


@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(), // Khởi tạo mặc định
    onNavigateToLogin: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    // 1. Thu thập state từ ViewModel
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RegisterScreenContent(
        uiState = uiState,
        onPhoneChanged = viewModel::onPhoneChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onAgreementChanged = viewModel::onAgreementChanged,
        onRegisterClick = viewModel::onRegisterClick,
        onRegisterGoogle = viewModel::onGoogleSignUp,
        onRegisterFacebook = viewModel::onFacebookSignUp,
        onNavigateToLogin = onNavigateToLogin
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenContent(
    uiState: RegisterUiState, // Nhận state từ bên ngoài
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onAgreementChanged: (Boolean) -> Unit,
    onRegisterClick: () -> Unit,
    onRegisterGoogle: () -> Unit,
    onRegisterFacebook: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(start = AppDimens.Padding.paddingMedium, top = AppDimens.Padding.paddingMedium) // Padding trái thay vì all
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
            Spacer(Modifier.height(24.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "ĐĂNG KÝ TÀI KHOẢN",
                    color = AppColor.Primary,
                    style = AppTextStyles.Heading1
                )
                Text(
                    text = "Bắt đầu hành trình thời trang bền vững",
                    color = AppColor.neutral500,
                    style = AppTextStyles.SupportText,
                    modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
                )
            }

            // Input Số điện thoại
            OutlinedTextField(
                value = uiState.phone, // Lấy giá trị từ state
                onValueChange = onPhoneChanged, // Gọi hàm từ VM
                label = { Text("Số điện thoại") },
                modifier = Modifier.fillMaxWidth()
                    .heightIn(min = 56.dp, max = 56.dp),
                shape = MaterialTheme.shapes.extraLarge
            )

            Spacer(Modifier.height(16.dp))

            // Input Mật khẩu
            OutlinedTextField(
                value = uiState.password,
                onValueChange = onPasswordChanged,
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
                modifier = Modifier.fillMaxWidth()
                    .heightIn(min = 56.dp, max = 56.dp), // ← Fix cứng cả min lẫn max
                shape = MaterialTheme.shapes.extraLarge
            )
            Spacer(Modifier.height(16.dp))

            // Checkbox Điều khoản
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = uiState.isAgreed,
                    onCheckedChange = onAgreementChanged,
                    colors = CheckboxDefaults.colors(
                        checkedColor = AppColor.Primary,      // Màu khi checked
                        uncheckedColor = AppColor.Primary,            // Màu viền khi unchecked
                        checkmarkColor = AppColor.OnPrimary             // Màu dấu ✓ bên trong
                ),
                    modifier = Modifier
                        .size(24.dp)           // ← Set size cố định cho checkbox
                        .padding(0.dp)         // ← Loại bỏ padding mặc định 8dp
                )
                Spacer(Modifier.width(8.dp))   // ← Khoảng cách có chủ đích, dễ kiểm soát

                Text(
                    text = "Đồng ý với ",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Điều khoản sử dụng",
                    style = AppTextStyles.Heading4,
                    color = AppColor.Primary,
                    textDecoration = TextDecoration.Underline
                )
            }
            Spacer(Modifier.height(24.dp))

            // Nút Đăng ký chính
            GradientButton(
                text = "Đăng ký",
                onClick = onRegisterClick,
                brush = AppBrush.SageGradient,
                enabled = !uiState.isLoading,
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

            Spacer(Modifier.height(32.dp))

            // Dòng "Hoặc"
            Row(verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(modifier = Modifier.weight(1f))
                Text("  Hoặc  ", color = Color.Gray, fontSize = 14.sp)
                HorizontalDivider(modifier = Modifier.weight(1f))
            }
            Spacer(Modifier.height(24.dp))

            // Nút Google & Facebook
            OutlinedButton(
                onClick = onRegisterGoogle,
                modifier = Modifier.fillMaxWidth().height(40.dp),
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Text("G", color = Color.Blue, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(8.dp))
                Text("Đăng ký bằng Google")
            }
            Spacer(Modifier.height(12.dp))
            OutlinedButton(
                onClick = onRegisterFacebook,
                modifier = Modifier.fillMaxWidth().height(40.dp),
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Text("f", color = Color(0xFF1877F2), fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(8.dp))
                Text("Đăng ký bằng Facebook")
            }

            Spacer(Modifier.height(32.dp))

            // Chuyển sang Đăng nhập
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Bạn đã có tài khoản? ", color = Color.Gray)
                TextButton(onClick = onNavigateToLogin) {
                    Text("Đăng nhập", color = Color(0xFF5C7A65))
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Preview
@Composable
fun PreviewRegisterScreen() {
    // Giả lập trạng thái UI để xem trước giao diện
    RegisterScreenContent(
        uiState = RegisterUiState(
            phone = "0912345678",
            password = "",
            isAgreed = true,
            isLoading = false
        ),
        onPhoneChanged = {},
        onPasswordChanged = {},
        onAgreementChanged = {},
        onRegisterClick = {},
        onNavigateToLogin = {},
        onRegisterGoogle = {},
        onRegisterFacebook = {},

    )
}