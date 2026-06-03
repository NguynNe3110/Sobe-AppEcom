package com.uzuu.sobe.feature.auth.welcome

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush
import com.example.ui.theme.AppColor
import com.uzuu.sobe.R
import com.uzuu.sobe.ui.component.button.GradientButton
import com.uzuu.sobe.ui.theme.AppDimens

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = viewModel(),
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    WelcomeScreenContent(
        onRegisterClick = onRegisterClick,
        onLoginClick = onLoginClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreenContent(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
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
                painter = painterResource(id = R.drawable.img_welcome),
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
                    text = ("MỪNG BẠN GHÉ SOBE"),
                    fontSize = 24.sp,
                    style = TextStyle(AppBrush.SageGradient) + AppTextStyles.Heading1,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Description
                Text(
                    text = ("Nơi mỗi món đồ đều có thể bắt đầu một vòng đời mới. Hãy cùng SOBE lan toả thói quen sống xanh, chia sẻ phong cách, và trao đi những giá trị bền vững."),
                    fontSize = 14.sp,
                    color = AppColor.neutral300,
                    textAlign = TextAlign.Center,
                    style = TextStyle(AppBrush.SageGradient),
                    lineHeight = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

            // Buttons Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppDimens.Padding.paddingExtraLarge),
                horizontalArrangement = Arrangement.spacedBy(AppDimens.Spacing.spacingLarge)
            ) {
                // Register Button (Outlined)
                OutlinedButton(
                    onClick = onRegisterClick,
                    modifier = Modifier.weight(1f).height(44.dp),
                    shape = RoundedCornerShape(AppDimens.Corner.cornerRadiusLarge),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = AppColor.Primary
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        width = 2.dp
                    ),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "Đăng ký",
                        fontSize = 16.sp,
                        style = AppTextStyles.Heading3 + TextStyle(brush = AppBrush.SageGradient )
                    )
                }

                GradientButton(
                    text = "Đăng nhập",
                    onClick = onLoginClick,
                    brush = AppBrush.SageGradient,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),  // ← CÙNG padding
                    textStyle = AppTextStyles.Heading3.copy(color = Color.White)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreenContent(
        onRegisterClick = {},
        onLoginClick = {}
    )
}