package com.uzuu.jetpack_compose_hub.feature.learn6_navigation.feature.auth.login


import android.R.attr.password
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import com.uzuu.sobe.feature.auth.login.LoginUiState
import com.uzuu.sobe.feature.auth.login.LoginViewModel
import com.uzuu.sobe.feature.auth.register.RegisterViewModel
import com.uzuu.sobe.ui.component.button.GradientButton
import com.uzuu.sobe.ui.theme.AppDimens


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(), // Khởi tạo mặc định
    onNavigateToRegister: () -> Unit,
    onNavigateToForgetPassword: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    // 1. Thu thập state từ ViewModel
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RegisterScreenContent(
        uiState = uiState,
        onPhoneChanged = viewModel::onPhoneChanged,
        onPasswordChanged = viewModel::onPasswordChanged,

        onGoogleLogin = viewModel::onGoogleLogin,
        onFacebookLogin = viewModel::onFacebookLogin,

        onNavigateToRegister = viewModel::onNavigateToRegister,
        onNavigateToHome = viewModel::onNavigateToHome,
        onNavigateToForgetPassword = viewModel::onNavigateToForgetPassword
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenContent(
    uiState: LoginUiState, // Nhận state từ bên ngoài
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,

    onGoogleLogin: () -> Unit,
    onFacebookLogin: () -> Unit,

    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToForgetPassword: () -> Unit
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
                    text = "ĐĂNG NHẬP TÀI KHOẢN",
                    color = AppColor.Primary,
                    style = TextStyle(AppBrush.SageGradient) + AppTextStyles.Heading1,
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

            Spacer(Modifier.height(24.dp))

            // Nút Đăng ký chính
            GradientButton(
                text = "Đăng nhập",
                onClick = onNavigateToHome,
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
                onClick = onGoogleLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp), // Tăng chiều cao lên chút cho cân đối (ảnh trông khoảng 48-50dp)
                border = BorderStroke(1.dp, AppColor.neutral300), // Dùng màu xám nhạt cho viền giống ảnh
                shape = RoundedCornerShape(percent = 50), // Bo tròn kiểu viên thuốc (Pill shape)
                contentPadding = PaddingValues(0.dp) // Xóa padding mặc định của OutlinedButton để dễ căn chỉnh
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center // QUAN TRỌNG: Căn cả hàng ra giữa
                ) {
                    // Icon Google - Bỏ tint để giữ nguyên màu sắc logo
                    Icon(
                        modifier = Modifier
                            .padding(start = 16.dp) // Padding trái thay vì all
                            .size(24.dp),           // Size icon chuẩn Material
                        painter = painterResource(id = R.drawable.logo_google),
                        contentDescription = "Back",
                        tint = Color.Unspecified // thuộc tính này cho phép loại bỏ lớp phủ mawjc điịnh của gg
                    )

                    Text(
                        text = "Đăng ký bằng Google",
                        color = AppColor.neutral300, // Màu chữ đen/xám đậm
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        textDecoration = TextDecoration.Underline
                    )

                    // Spacer vô hình bên phải để bù trừ cho padding start của icon,
                    // giúp chữ "Đăng ký..." nằm CHÍNH GIỮA nút
                    Spacer(modifier = Modifier.width(40.dp))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = onFacebookLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp), // Tăng chiều cao lên chút cho cân đối (ảnh trông khoảng 48-50dp)
                border = BorderStroke(1.dp, AppColor.neutral300), // Dùng màu xám nhạt cho viền giống ảnh
                shape = RoundedCornerShape(percent = 50), // Bo tròn kiểu viên thuốc (Pill shape)
                contentPadding = PaddingValues(0.dp) // Xóa padding mặc định của OutlinedButton để dễ căn chỉnh
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center // QUAN TRỌNG: Căn cả hàng ra giữa
                ) {
                    // Icon Google - Bỏ tint để giữ nguyên màu sắc logo
                    Icon(
                        modifier = Modifier
                            .padding(start = 16.dp) // Padding trái thay vì all
                            .size(24.dp),           // Size icon chuẩn Material
                        painter = painterResource(id = R.drawable.logo_facebook),
                        contentDescription = "Back",
                        tint = Color.Unspecified // thuộc tính này cho phép loại bỏ lớp phủ mawjc điịnh của gg
                    )

                    Text(
                        text = "Đăng ký bằng Facebook",
                        color = AppColor.neutral300, // Màu chữ đen/xám đậm
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )

                    // Spacer vô hình bên phải để bù trừ cho padding start của icon,
                    // giúp chữ "Đăng ký..." nằm CHÍNH GIỮA nút
                    Spacer(modifier = Modifier.width(40.dp))
                }
            }

            Spacer(Modifier.height(32.dp))

            // Chuyển sang Đăng nhập
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Bạn chưa có tài khoản? ",
                    color = AppColor.black,
                    style = AppTextStyles.Label,
                )
                TextButton(onClick = onNavigateToRegister) {
                    Text("Đăng ký", color = Color(0xFF5C7A65))
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
        uiState = LoginUiState(
            phone = "0912345678",
            password = "",
            isLoading = false
        ),
        onPhoneChanged = {},
        onPasswordChanged = {},
        onGoogleLogin = {},
        onFacebookLogin = {},
        onNavigateToRegister = {},
        onNavigateToHome = {},
        onNavigateToForgetPassword = {},

        )
}