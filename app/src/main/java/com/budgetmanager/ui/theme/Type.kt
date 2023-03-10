package com.budgetmanager.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.budgetmanager.R

val ComfortaaFontFamily = FontFamily(
    Font(R.font.comfortaa_bold),
    Font(R.font.comfortaa_light),
    Font(R.font.comfortaa_medium),
    Font(R.font.comfortaa_regular),
    Font(R.font.comfortaa_semibold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = ComfortaaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontFamily = ComfortaaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = ComfortaaFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = ComfortaaFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 22.sp
    ),
    h6 = TextStyle(
        fontFamily = ComfortaaFontFamily,
        fontWeight = FontWeight.W500,
        color = Color.DarkGray,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = ComfortaaFontFamily,
        fontWeight = FontWeight.W200,
        color = Color.DarkGray,
        fontSize = 12.sp
    )
)