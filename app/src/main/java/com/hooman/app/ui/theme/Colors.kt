package com.hooman.app.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme

// Utility: AdaptiveColor to handle light/dark
data class AdaptiveColor(
    val light: Color,
    val dark: Color
) {
    @Composable
    fun resolve(): Color {
        return if (isSystemInDarkTheme()) dark else light
    }
}

// Utility: hex to Color
fun Color.Companion.fromHex(hex: String): Color {
    val cleanHex = hex.removePrefix("#")
    val colorLong = cleanHex.toLong(16)
    return when (cleanHex.length) {
        6 -> Color(0xFF000000 or colorLong)
        8 -> Color(colorLong)
        else -> Color.Black
    }
}
val HoomanOrange = Color(0xFFE95744)
val HoomanWhite = Color(0xFFFDFCFB)
val logoColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) HoomanWhite else HoomanOrange

// Main color object
object HoomanColors {

    // Primary Brand
    val hoomanOrange = Color.fromHex("E95744")
    val veterinaryTeal = Color.fromHex("14B8A6")
    val pureWhite = Color.White
    val deepCharcoal = Color.fromHex("1E1E1E")

    // Background
    val bgPrimary = AdaptiveColor(
        light = Color.fromHex("F5F9FF"),
        dark = Color.Black
    )
    val bgSecondary = AdaptiveColor(
        light = Color.White,
        dark = Color.fromHex("2A2A2A")
    )
    val bgTertiary = AdaptiveColor(
        light = Color.fromHex("EAEFF7"),
        dark = Color.fromHex("333333")
    )
    val bgCard = AdaptiveColor(
        light = Color.White,
        dark = Color.fromHex("2A2A2A")
    )
    val bgInput = AdaptiveColor(
        light = Color.White,
        dark = Color.fromHex("2A2A2A")
    )
    val bgInputSubtle = AdaptiveColor(
        light = Color.fromHex("F8FAFC"),
        dark = Color.fromHex("333333")
    )
    val bgOverlay = AdaptiveColor(
        light = Color.Black.copy(alpha = 0.5f),
        dark = Color.Black.copy(alpha = 0.7f)
    )

    // Text
    val textPrimary = AdaptiveColor(
        light = Color.fromHex("1E1E1E"),
        dark = Color.White
    )
    val textSecondary = AdaptiveColor(
        light = Color.fromHex("6B7280"),
        dark = Color.fromHex("A3A3A3")
    )
    val textTertiary = AdaptiveColor(
        light = Color.fromHex("9CA3AF"),
        dark = Color.fromHex("737373")
    )
    val textInverse = AdaptiveColor(
        light = Color.White,
        dark = Color.fromHex("1E1E1E")
    )

    // Accents
    val accentOrange = Color.fromHex("E95744")
    val accentOrangeLight = Color.fromHex("FF6B35")
    val accentTeal = Color.fromHex("14B8A6")
    val accentOrangePressed = Color.fromHex("D63E2A")
    val accentTealPressed = Color.fromHex("0F766E")
    val accentGold = Color.fromHex("F59E0B")
    val accentBlue = Color.fromHex("3B82F6")

    // Borders
    val borderPrimary = AdaptiveColor(
        light = Color.fromHex("E2E8F0"),
        dark = Color.fromHex("404040")
    )
    val borderSecondary = AdaptiveColor(
        light = Color.fromHex("CBD5E0"),
        dark = Color.fromHex("4A4A4A")
    )
    val borderSubtle = AdaptiveColor(
        light = Color.fromHex("E2E8F0").copy(alpha = 0.6f),
        dark = Color.fromHex("404040")
    )
    val borderError = Color.fromHex("EF4444")
    val borderSuccess = Color.fromHex("10B981")

    // Semantic
    val successGreen = Color.fromHex("10B981")
    val warningAmber = Color.fromHex("F59E0B")
    val errorRed = Color.fromHex("DC2626")
    val infoBlue = Color.fromHex("3B82F6")

    // Header
    val headerBackground = AdaptiveColor(
        light = Color.White,
        dark = Color.fromHex("1C1C1E")
    )

    // Interactive states
    val pressed = Color.Black.copy(alpha = 0.1f)
    val hover = Color.White.copy(alpha = 0.05f)
    val focus = accentOrange.copy(alpha = 0.2f)
    val disabled = Color.fromHex("4A5568")

    // Example service accent colors
    fun accentColorFor(type: String): Color = when (type) {
        "vet" -> veterinaryTeal
        "emergency" -> Color.fromHex("DC3545")
        "grooming" -> Color.fromHex("FFC107")
        "shop" -> Color.fromHex("17A2B8")
        "cafe" -> Color.fromHex("6F42C1")
        else -> accentBlue
    }
}