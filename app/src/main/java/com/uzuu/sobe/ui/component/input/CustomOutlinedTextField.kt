//package com.uzuu.sobe.ui.component.input
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.runtime.Composable
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//
//@Composable
//fun CustomOutlinedTextField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    label: String,
//    modifier: Modifier = Modifier,
//    height: Dp = 40.dp, // Có thể điều chỉnh
//    cornerRadius: Dp = 8.dp,
//    borderColor: Color = Color.Gray,
//    focusedBorderColor: Color = MaterialTheme.colorScheme.primary,
//    textColor: Color = Color.Black,
//    labelColor: Color = Color.Gray,
//    enabled: Boolean = true,
//    isError: Boolean = false,
//    errorMessage: String? = null,
//    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//    visualTransformation: VisualTransformation = VisualTransformation.None,
//    leadingIcon: @Composable (() -> Unit)? = null,
//    trailingIcon: @Composable (() -> Unit)? = null
//) {
//    var isFocused by remember { mutableStateOf(false) }
//
//    val currentBorderColor = when {
//        isError -> MaterialTheme.colorScheme.error
//        isFocused -> focusedBorderColor
//        else -> borderColor
//    }
//
//    Column(modifier = modifier) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(height)
//                .border(
//                    width = 1.dp,
//                    color = currentBorderColor,
//                    shape = RoundedCornerShape(cornerRadius)
//                )
//                .background(
//                    color = if (enabled) Color.White else Color.LightGray.copy(alpha = 0.3f),
//                    shape = RoundedCornerShape(cornerRadius)
//                )
//                .clickable(enabled = enabled) { /* Focus handling nếu cần */ },
//            contentAlignment = Alignment.CenterStart
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 12.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                // Leading Icon
//                if (leadingIcon != null) {
//                    Spacer(modifier = Modifier.width(4.dp))
//                    leadingIcon()
//                    Spacer(modifier = Modifier.width(8.dp))
//                }
//
//                // Input Field
//                BasicTextField(
//                    value = value,
//                    onValueChange = onValueChange,
//                    enabled = enabled,
//                    textStyle = LocalTextStyle.current.copy(
//                        color = textColor,
//                        fontSize = 14.sp
//                    ),
//                    keyboardOptions = keyboardOptions,
//                    visualTransformation = visualTransformation,
//                    modifier = Modifier.weight(1f),
//                    decorationBox = { innerTextField ->
//                        Box(
//                            modifier = Modifier.fillMaxWidth(),
//                            contentAlignment = Alignment.CenterStart
//                        ) {
//                            // Label floating hoặc static
//                            if (value.isEmpty() && !isFocused) {
//                                Text(
//                                    text = label,
//                                    color = labelColor,
//                                    fontSize = 14.sp
//                                )
//                            }
//                            innerTextField()
//                        }
//                    }
//                )
//
//                // Trailing Icon
//                if (trailingIcon != null) {
//                    Spacer(modifier = Modifier.width(8.dp))
//                    trailingIcon()
//                }
//            }
//
//            // Focus detector
//            PointerInput(Unit) {
//                detectTapGestures(
//                    onTap = { isFocused = true },
//                    onPress = {
//                        isFocused = true
//                        tryAwaitRelease()
//                        isFocused = false
//                    }
//                )
//            }
//        }
//
//        // Error Message
//        if (isError && !errorMessage.isNullOrEmpty()) {
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = errorMessage,
//                color = MaterialTheme.colorScheme.error,
//                fontSize = 12.sp,
//                modifier = Modifier.padding(start = 4.dp)
//            )
//        }
//    }
//}