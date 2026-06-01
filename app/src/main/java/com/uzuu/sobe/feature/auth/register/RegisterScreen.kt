package com.uzuu.jetpack_compose_hub.feature.learn6_navigation.feature.auth.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(
    onNavigateBack: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "This is Register",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onRegisterSuccess) {
            Text("Register (Go to Login)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onNavigateBack) {
            Text("Go Back to Login")
        }
    }
}