package com.uzuu.sobe.feature.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sobe.ui.theme.ProstoOneFontFamily
import com.example.ui.theme.AppColor
import kotlinx.coroutines.delay
import com.uzuu.sobe.R

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    var phase by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = true) {
        delay(500)
        phase = 1 // Background expand

        delay(1000)
        phase = 2 // Text biến mất, logo ở giữa

        delay(900)
        phase = 3 // Logo trượt trái,

        delay(800)

        phase = 4 // text xuất hiện bên cạnh

        delay(800)
        phase = 5 // Column dịch lên, slogan hiện ra

        delay(3300)
        onSplashFinished()
    }

    // Background Circle Scale
    val bgScale by animateFloatAsState(
        targetValue = if (phase >= 1) 15f else 1.8f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "bgScale"
    )

    val textOnsetX by animateDpAsState(
        targetValue = if (phase >= 3) 45.dp else 0.dp,
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing),
        label = "logoOffsetX"
    )

    // Logo Offset X - trượt sang trái ở phase 3
    val logoOffsetX by animateDpAsState(
        targetValue = if (phase >= 3) (-75).dp else 0.dp,
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing),
        label = "logoOffsetX"
    )

    // Logo Offset Y - dịch lên ở phase 4
    val logoOffsetY by animateDpAsState(
        targetValue = if (phase >= 4) (-40).dp else 0.dp,
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing),
        label = "logoOffsetY"
    )

    // logo hạ xuống
    val logoOnsetY by animateDpAsState(
        targetValue = if (phase >= 2) (20).dp else 0.dp,
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing),
        label = "logoOffsetY"
    )
    // Text Logo Alpha - biến mất ở phase 2, xuất hiện lại ở phase 3
    val textLogoAlpha by animateFloatAsState(
        targetValue = when (phase) {
            2 -> 0f      // Phase 2: biến mất
            4 -> 1f   // Phase 3-4: xuất hiện
            else -> 1f   // Phase 0-1: hiển thị ban đầu
        },
        animationSpec = tween(durationMillis = 300),
        label = "textLogoAlpha"
    )

    // Slogan Alpha - chỉ hiện ở phase 4
    val sloganAlpha by animateFloatAsState(
        targetValue = if (phase >= 5) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "sloganAlpha"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Background Circle
        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(scaleX = bgScale, scaleY = bgScale)
                .clip(CircleShape)
                .background(AppColor.Primary)
        )
        Row(
            verticalAlignment = Alignment.Top, // ← đổi ở đây
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .offset {
                IntOffset(logoOffsetX.roundToPx(), logoOffsetY.roundToPx())
            }
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_sobe),
                    contentDescription = null,
                    modifier = Modifier
                        .height(52.dp),
                    contentScale = ContentScale.Fit
                )

                if(phase < 2) {
                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "SOBE",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.alpha(textLogoAlpha),
                        style = TextStyle(
                            fontFamily = ProstoOneFontFamily
                        )
                    )
                }
            }
        }
        if(phase >= 3) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = "SOBE",
                color = AppColor.Beige,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
//                modifier = if (phase < 2) {
//                    Modifier.size(0.dp)  // Biến mất hoàn toàn
//                } else {
//                    Modifier.alpha(textLogoAlpha)
//                },
                modifier = Modifier.alpha(textLogoAlpha)
                    .offset {
                        IntOffset(textOnsetX.roundToPx(),logoOffsetY.roundToPx())
                    },
                style = TextStyle(
                    fontFamily = ProstoOneFontFamily
                )
            )
        }
        // Slogan - chỉ hiện ở phase 4
        Text(
            text = "SỐNG XANH - BỀN VỮNG",
            color = AppColor.OnPrimary.copy(alpha = 0.9f),
            fontSize = 12.sp,
            modifier = Modifier
                .alpha(sloganAlpha)
                .padding(top = 8.dp),
            letterSpacing = 5.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    MaterialTheme {
        Surface {
            SplashScreen(onSplashFinished = {})
        }
    }
}

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    SplashScreen(onSplashFinished = {
                        // Navigate to main activity
                    })
                }
            }
        }
    }
}