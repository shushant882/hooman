package com.hooman.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hooman.app.R

// Load your Urbanist font from res/font
val Urbanist = FontFamily(
    Font(R.font.urbanist_light, FontWeight.Light),
    Font(R.font.urbanist_regular, FontWeight.Normal),
    Font(R.font.urbanist_medium, FontWeight.Medium),
    Font(R.font.urbanist_semibold, FontWeight.SemiBold),
    Font(R.font.urbanist_bold, FontWeight.Bold)
)

// Helper to build consistent styles
private fun hoomanTextStyle(
    weight: FontWeight,
    size: Int,
    lineHeight: Double,
    letterSpacing: Double
) = TextStyle(
    fontFamily = Urbanist,
    fontWeight = weight,
    fontSize = size.sp,
    lineHeight = lineHeight.sp,
    letterSpacing = letterSpacing.sp
)

// Typography Specification for the Hooman App Theme
val HoomanTypography = Typography(
    displayLarge = hoomanTextStyle(FontWeight.Bold, 40, 42.0, -0.5),
    displayMedium = hoomanTextStyle(FontWeight.Bold, 34, 37.4, -0.4),
    displaySmall = hoomanTextStyle(FontWeight.Bold, 28, 32.2, -0.3),

    headlineLarge = hoomanTextStyle(FontWeight.Bold, 28, 32.2, -0.3),
    headlineMedium = hoomanTextStyle(FontWeight.SemiBold, 22, 26.4, -0.2),
    headlineSmall = hoomanTextStyle(FontWeight.SemiBold, 20, 25.0, -0.1),

    titleLarge = hoomanTextStyle(FontWeight.SemiBold, 22, 26.4, -0.2),
    titleMedium = hoomanTextStyle(FontWeight.SemiBold, 17, 22.1, 0.0),
    titleSmall = hoomanTextStyle(FontWeight.Medium, 16, 20.8, 0.0),

    bodyLarge = hoomanTextStyle(FontWeight.Normal, 17, 23.8, 0.0),
    bodyMedium = hoomanTextStyle(FontWeight.Normal, 16, 21.6, 0.0),
    bodySmall = hoomanTextStyle(FontWeight.Normal, 15, 18.75, 0.0),

    labelLarge = hoomanTextStyle(FontWeight.Medium, 16, 20.8, 0.0),
    labelMedium = hoomanTextStyle(FontWeight.Medium, 12, 13.8, 0.0),
    labelSmall = hoomanTextStyle(FontWeight.Normal, 11, 12.1, 0.0),
)
