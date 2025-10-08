package com.hooman.app.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme

/**
 * A helper data class to hold separate color values for light and dark themes.
 * @param light The color to be used in the light theme.
 * @param dark The color to be used in the dark theme.
 */
data class AdaptiveColor(
    val light: Color,
    val dark: Color
) {
    /**
     * A Composable function that returns the correct color (light or dark)
     * based on the system's current theme settings.
     */
    @Composable
    fun resolve(): Color {
        return if (isSystemInDarkTheme()) dark else light
    }
}

/**
 * An extension function for the Color companion object that allows creating a Color
 * object directly from a hex string (e.g., "E95744").
 * It supports 6-digit (RGB) and 8-digit (ARGB) hex codes.
 */
fun Color.Companion.fromHex(hex: String): Color {
    // Remove the '#' prefix if it exists.
    val cleanHex = hex.removePrefix("#")
    // Convert the hex string to a Long.
    val colorLong = cleanHex.toLong(16)
    // Create the Color object based on the hex length.
    return when (cleanHex.length) {
        6 -> Color(0xFF000000 or colorLong) // 6-digit RGB, add full alpha.
        8 -> Color(colorLong)               // 8-digit ARGB.
        else -> Color.Black                 // Fallback color.
    }
}

// --- Static Color Definitions ---

// A static definition for your brand's orange color.
val HoomanOrange = Color(0xFFE95744)
// A static definition for an off-white color.
val HoomanWhite = Color(0xFFFDFCFB)

/**
 * A Composable property that dynamically provides a color for your logo.
 * It returns HoomanWhite in dark theme and HoomanOrange in light theme.
 */
val logoColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) HoomanWhite else HoomanOrange

/**
 * A central object (singleton) that holds your entire design system color palette.
 * This makes it easy to access colors consistently throughout the app.
 */
object HoomanColors {

    // --- Primary Brand Colors ---
    // These colors define the core identity of your brand.
    val hoomanOrange = Color.fromHex("E95744")
    val veterinaryTeal = Color.fromHex("14B8A6")
    val pureWhite = Color.White
    val deepCharcoal = Color.fromHex("1E1E1E")

    // --- Background Colors ---
    // These use AdaptiveColor to change based on the theme.
    val bgPrimary = AdaptiveColor(
        light = Color.fromHex("F5F9FF"), // A very light, almost white blue
        dark = Color.Black               // Pure black
    )
    val bgSecondary = AdaptiveColor(
        light = Color.White,
        dark = Color.fromHex("2A2A2A")   // Dark charcoal gray
    )
    val bgTertiary = AdaptiveColor(
        light = Color.fromHex("EAEFF7"), // Light cool gray
        dark = Color.fromHex("333333")   // Medium-dark gray
    )
    val terminal = AdaptiveColor(
        light = Color.fromHex("2A2A2A"), // Light cool gray
        dark = Color.fromHex("333333")   // Medium-dark gray
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
        light = Color.fromHex("F8FAFC"), // Very light off-white
        dark = Color.fromHex("333333")
    )
    val bgOverlay = AdaptiveColor(
        light = Color.Black.copy(alpha = 0.5f), // Semi-transparent black for light theme overlays
        dark = Color.Black.copy(alpha = 0.7f)  // More opaque black for dark theme overlays
    )

    // --- Text Colors ---
    // Adaptive colors for text to ensure readability on different backgrounds.
    val textPrimary = AdaptiveColor(
        light = Color.fromHex("1E1E1E"), // Almost-black for primary text
        dark = Color.White               // White for primary text
    )
    val textSecondary = AdaptiveColor(
        light = Color.fromHex("6B7280"), // Medium slate gray for less important text
        dark = Color.fromHex("A3A3A3")   // Light gray for less important text
    )
    val textTertiary = AdaptiveColor(
        light = Color.fromHex("9CA3AF"), // Lighter gray for hints or tertiary info
        dark = Color.fromHex("737373")   // Medium gray
    )
    val textInverse = AdaptiveColor(
        light = Color.White,             // For text on dark/colored backgrounds
        dark = Color.fromHex("1E1E1E")   // For text on light/colored backgrounds
    )

    // --- Accent Colors ---
    // Bright, static colors used for highlights, buttons, and special UI elements.
    val accentOrange = Color.fromHex("E95744")
    val accentOrangeLight = Color.fromHex("FF6B35")
    val accentTeal = Color.fromHex("14B8A6")
    val accentOrangePressed = Color.fromHex("D63E2A") // A darker shade for press-states
    val accentTealPressed = Color.fromHex("0F766E")
    val accentGold = Color.fromHex("F59E0B")
    val accentBlue = Color.fromHex("3B82F6")

    // --- Border Colors ---
    // Used for dividers, outlines on cards, and input fields.
    val borderPrimary = AdaptiveColor(
        light = Color.fromHex("E2E8F0"), // Light gray
        dark = Color.fromHex("404040")   // Dark gray
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

    // --- Semantic Colors ---
    // Colors that convey a specific meaning (e.g., success, failure).
    val successGreen = Color.fromHex("10B981")
    val warningAmber = Color.fromHex("F59E0B")
    val errorRed = Color.fromHex("DC2626")
    val infoBlue = Color.fromHex("3B82F6")

    // --- Header Specific Colors ---
    val headerBackground = AdaptiveColor(
        light = Color.White,
        dark = Color.fromHex("1C1C1E")
    )

    // --- Interactive State Colors ---
    // Overlays or colors for different UI interaction states.
    val pressed = Color.Black.copy(alpha = 0.1f)
    val hover = Color.White.copy(alpha = 0.05f)
    val focus = accentOrange.copy(alpha = 0.2f)
    val disabled = Color.fromHex("4A5568")

    /**
     * A helper function to return a specific accent color based on a given type.
     * Useful for dynamically coloring items based on their category.
     */
    fun accentColorFor(type: String): Color = when (type) {
        "vet" -> veterinaryTeal
        "emergency" -> Color.fromHex("DC3545")
        "grooming" -> Color.fromHex("FFC107")
        "shop" -> Color.fromHex("17A2B8")
        "cafe" -> Color.fromHex("6F42C1")
        else -> accentBlue
    }
}


// --- COLORS FOR THE FEATURE SCREENS ---
// This section defines a separate, more specific palette.
// 'internal' means these colors are only visible within this specific Gradle module.

// --- Dark Theme Colors (Feature Screens) ---
internal val DarkBackground = Color(0xFF000000)
internal val DarkCardBackground = Color(0xFF1C1C1E)
internal val DarkTextPrimary = Color(0xFFFFFFFF)
internal val DarkTextSecondary = Color(0xFF8E8E93)
internal val DarkAiBubbleColor = Color(0xFF2C2C2E)
internal val DarkProgressInactive = Color.White.copy(alpha = 0.3f)

// --- Light Theme Colors (Feature Screens) ---
internal val LightBackground = Color(0xFFF2F2F7)
internal val LightCardBackground = Color(0xFFFFFFFF)
internal val LightTextPrimary = Color(0xFF000000)
internal val LightTextSecondary = Color(0xFF6E6E73)
internal val LightAiBubbleColor = Color(0xFFE5E5EA)
internal val LightProgressInactive = Color.Black.copy(alpha = 0.1f)

// --- Shared Colors (Feature Screens) ---
// These colors are the same in both light and dark themes for this feature.
internal val AccentPurple = Color(0xFFBF5AF2)
internal val AccentCyan = Color(0xFF64D2FF)
internal val AccentGreen = Color(0xFF32D74B)
internal val UserBubbleColor = Color(0xFFF94336)