package com.uzuu.sobe.feature.auth.forgetPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.theme.AppBrush

@Composable
fun Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Box(
            modifier = Modifier
                .size(300.dp)
                .background(AppBrush.DangerGradient)
        )

        Box(
            modifier = Modifier
                .size(300.dp)
                .background(AppBrush.SageGradient)
        )
    }
}

@Preview
@Composable
fun p( ) {
    Surface {
        Screen()
    }
}