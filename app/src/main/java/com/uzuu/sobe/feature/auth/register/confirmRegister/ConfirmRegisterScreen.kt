package com.uzuu.sobe.feature.auth.register.confirmRegister

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
fun ConfirmRegisterScreen(
    viewModel: ConfirmRegisterViewModel = hiltViewModel(),
    onClickComfirm: () -> Unit,
    onRequestAgain: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // thu thaajp uistate
    ConfirmRegisterScreenContent(
        uiState = uiState,
//        onClickComfirm = viewModel::onClickComfirm,
//        onRequestAgain = viewModel::onRequestAgain
        onClickComfirm = onClickComfirm,
        onRequestAgain = onRequestAgain
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmRegisterScreenContent(
    uiState: ConfirmRegisterUiState,
    onClickComfirm: () -> Unit,
    onRequestAgain: () -> Unit
) {
    var otpCode by remember { mutableStateOf("") }

    // State giả lập thời gian đếm ngược (ví dụ: 120 giây = 2 phút)
    var timeLeft by remember { mutableIntStateOf(120) }

    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            kotlinx.coroutines.delay(1000L)
            timeLeft--
        }
    }

    // Format thời gian mm:ss
    val formattedTime = String.format("%02d:%02d", timeLeft / 60, timeLeft % 60)
    val isTimerActive = timeLeft > 0

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
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = AppDimens.Spacing.spacingExtraLarge)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppDimens.Spacing.spacingExtraLarge)
        ) {
            Spacer(modifier = Modifier.height(AppDimens.Spacing.spacingExtraLarge))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(AppDimens.Spacing.spacingExtraSmall)
            ) {
                Text(
                    text = "XÁC NHẬN ĐĂNG KÝ",
                    color = AppColor.Primary,
                    style = AppTextStyles.Heading1
                )
                Text(
                    text = "Mã xác nhận đã được gửi tới số điện thoại của bạn. \n" +
                            "Vui lòng kiểm tra và điền chính xác mã xác nhận.",
                    color = AppColor.neutral500,
                    style = AppTextStyles.SupportText,
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(AppDimens.Spacing.spacingTwenty),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(4) { index ->
                        val char = otpCode.getOrNull(index)?.toString() ?: ""

                        BasicTextField(
                            value = char,
                            onValueChange = { newValue ->
                                if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                                    val newOtp = otpCode.toCharArray().apply {
                                        if (index < 4) this[index] = newValue.first()
                                    }.joinToString("").take(4)
                                    otpCode = newOtp
                                }
                            },
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center, // ← Chỉ căn ngang
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
                                    contentAlignment = Alignment.Center, // ← CĂN GIỮA CẢ NGANG LẪN DỌC
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    innerTextField()
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Mã còn hiệu lực trong ", style = AppTextStyles.SupportText, color = AppColor.neutral600)
                    Text("01:52 phút", style = AppTextStyles.Label, color = AppColor.neutral300)
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
// 5. Nút Xác nhận
                GradientButton(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    text = "Xác nhận",
                    brush = AppBrush.SageGradient,
                    onClick = onClickComfirm,
//                    enabled = otpCode.length == 4, // Chỉ bật khi đủ 4 số
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    shape = RoundedCornerShape(25.dp), // Bo tròn kiểu pill
                    textStyle = AppTextStyles.Heading3.copy(color = Color.White),
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 6. Resend Code
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Bạn đã nhận được mã?", style = AppTextStyles.Title, color = AppColor.black)
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = onRequestAgain, enabled = !isTimerActive) {
                        Text(
                            "Gửi lại mã",
                            fontSize = 14.sp,
                            style = AppTextStyles.Heading4,
                            color = if (isTimerActive) AppColor.neutral500 else AppColor.Primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
fun PreviewConfirmScreen() {
    ConfirmRegisterScreenContent(
        ConfirmRegisterUiState(
            isLoading = true
        ),
        onClickComfirm = {},
        onRequestAgain = {}
    )
}