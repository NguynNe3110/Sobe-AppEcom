package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object ColorProjectOther{
    val Purple80 = Color(0xFFD0BCFF)
    val PurpleGrey80 = Color(0xFFCCC2DC)
    val Pink80 = Color(0xFFEFB8C8)

    val Purple40 = Color(0xFF6650a4)
    val PurpleGrey40 = Color(0xFF625b71)
    val Pink40 = Color(0xFF7D5260)

    // Elegant, gentle, relaxing pastel colors
    val PastelCyan = Color(0xFFE6FFFF)
    val PastelGreen = Color(0xFFF9FFF5)
    val PastelYellow = Color(0xFFFFFDF0)
    val PastelRed = Color(0xFFFFF5F5)

    val TextPrimary = Color(0xFF212121)
    val TextSecondary = Color(0xFF757575)
    val EmeraldGreen = Color(0xFF2E7D32)
    val CrimsonRed = Color(0xFFC62828)
    val SoftBackground = Color(0xFFFAFAFA)
}



    //color branch/ olive
    val Olive1000 = Color(0xFFECEFE7)
    val Olive900 = Color(0xFFDDE2D4)
    val Olive800 = Color(0xFFCAD2BC)
    val Olive700 = Color(0xFFB7C2A3)
    val Olive600 = Color(0xFFA3B18A)
    val Olive500 = Color(0xFF8C9D6C)
    val Olive400 = Color(0xFF718156)
    val Olive300 = Color(0xFF566241)
    val Olive200 = Color(0xFF3B432D)
    val Olive100 = Color(0xFF202518)

    //color brand/sage
    val sage100 = Color(0xFF283126)
    val sage200 = Color(0xFF3A4837)
    val sage300 = Color(0xFF4D5F49)
    val sage400 = Color(0xFF5F765B)
    val sage500 = Color(0xFF718D6D)
    val sage600 = Color(0xFF869F82)
    val sage700 = Color(0xFF9DB19A)
    val sage800 = Color(0xFFB9C7B7)
    val sage900 = Color(0xFFD5DED4)
    val sage1000 = Color(0xFFF1F4F1)



    //other color
    val dangerRed400 = Color(0xFFBD0F0F)
    val darkRed = Color(0xFFCA6549) // secondary
    val beige = Color(0xFFFEF8F1)

object AppColor{
    // Primary - Sage
    val Primary = sage500
    val OnPrimary = Color.White
    val Secondary = darkRed

    val PrimaryVariant = Color(0xFF5F765B)
    val PrimaryLight = Color(0xFFB9C7B7)
    val PrimaryDark = Color(0xFF4D5F49)

    // Background & Surface
    val Background = Color(0xFFF2F2F2)
    val Surface = Color.White
    val OnBackground = Color(0xFF1F1F1F)
    val OnSurface = Color(0xFF1F1F1F)

    // Error & Warning
    val Error = Color(0xFFBD0F0F)
    val OnError = Color.White
    val Warning = Color(0xFFCA6549)
    val OnWarning = Color.White

    // Borders
    val Border = Color(0xFFBFBFBF)
    val Divider = Color(0xFFDBDBDB)

    // Text
    val TextPrimary = Color(0xFF1F1F1F)
    val TextSecondary = Color(0xFF595959)
    val TextDisabled = Color(0xFFA6A6A6)

    // neutual gray
    val neutral1000 = Color(0xFFF2F2F2)
    val neutral900 = Color(0xFFDBDBDB)
    val neutral800 = Color(0xFFBFBFBF)
    val neutral700 = Color(0xFFA6A6A6)
    val neutral600 = Color(0xFF8C8C8C)
    val neutral500 = Color(0xFF737373)
    val neutral400 = Color(0xFF595959)
    val neutral300 = Color(0xFF404040)
    val neutral200 = Color(0xFF2E2E2E)
    val neutral100 = Color(0xFF1F1F1F)

    val black = Color(0xFF000000)
}

object AppBrush {
    val DangerGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFFEA5F),
            Color(0xFFFFB730)
        )
    )

    val SageGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF869F82),
            Color(0xFF5F765B)
        )
    )
}
