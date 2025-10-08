package com.hooman.app.ui.theme // Or your preferred theme package

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.hooman.app.ui.components.HoomanColors // Assuming your file is in this package

// 1. DATA CLASS TO HOLD THE RESOLVED THEME COLORS
// This makes it easy to access colors like: HoomanTheme.colors.backgroundPrimary
data class AppColors(
    // Background
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val backgroundTertiary: Color,
    val cardBackground: Color,
    val inputBackground: Color,
    val overlayBackground: Color,

    // Text
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textInverse: Color,

    // Borders
    val borderPrimary: Color,
    val borderSecondary: Color,
    val borderSubtle: Color,

    // Brand & Accents (Many are static, but we include them for consistency)
    val brand: Color,
    val accent: Color,
    val brandPressed: Color,
    val success: Color,
    val warning: Color,
    val error: Color,
    val info: Color
)

// 2. DEFINE LIGHT AND DARK PALETTES USING YOUR HoomanColors OBJECT
private val LightColorPalette = AppColors(
    // Background
    backgroundPrimary = HoomanColors.bgPrimary.light,
    backgroundSecondary = HoomanColors.bgSecondary.light,
    backgroundTertiary = HoomanColors.bgTertiary.light,
    cardBackground = HoomanColors.bgCard.light,
    inputBackground = HoomanColors.bgInput.light,
    overlayBackground = HoomanColors.bgOverlay.light,

    // Text
    textPrimary = HoomanColors.textPrimary.light,
    textSecondary = HoomanColors.textSecondary.light,
    textTertiary = HoomanColors.textTertiary.light,
    textInverse = HoomanColors.textInverse.light,

    // Borders
    borderPrimary = HoomanColors.borderPrimary.light,
    borderSecondary = HoomanColors.borderSecondary.light,
    borderSubtle = HoomanColors.borderSubtle.light,

    // Accents
    brand = HoomanColors.hoomanOrange,
    accent = HoomanColors.accentTeal,
    brandPressed = HoomanColors.accentOrangePressed,
    success = HoomanColors.successGreen,
    warning = HoomanColors.warningAmber,
    error = HoomanColors.errorRed,
    info = HoomanColors.infoBlue
)

private val DarkColorPalette = AppColors(
    // Background
    backgroundPrimary = HoomanColors.bgPrimary.dark,
    backgroundSecondary = HoomanColors.bgSecondary.dark,
    backgroundTertiary = HoomanColors.bgTertiary.dark,
    cardBackground = HoomanColors.bgCard.dark,
    inputBackground = HoomanColors.bgInput.dark,
    overlayBackground = HoomanColors.bgOverlay.dark,

    // Text
    textPrimary = HoomanColors.textPrimary.dark,
    textSecondary = HoomanColors.textSecondary.dark,
    textTertiary = HoomanColors.textTertiary.dark,
    textInverse = HoomanColors.textInverse.dark,

    // Borders
    borderPrimary = HoomanColors.borderPrimary.dark,
    borderSecondary = HoomanColors.borderSecondary.dark,
    borderSubtle = HoomanColors.borderSubtle.dark,

    // Accents
    brand = HoomanColors.hoomanOrange,
    accent = HoomanColors.accentTeal,
    brandPressed = HoomanColors.accentOrangePressed,
    success = HoomanColors.successGreen,
    warning = HoomanColors.warningAmber,
    error = HoomanColors.errorRed,
    info = HoomanColors.infoBlue
)

// 3. CREATE COMPOSITION LOCAL
private val LocalAppColors = staticCompositionLocalOf { LightColorPalette }

// 4. CREATE THEME OBJECT FOR EASY ACCESS
object HoomanTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
    // You can also add Typography and Shapes here
    // val typography: AppTypography @Composable get() = LocalAppTypography.current
}

// 5. CREATE THE MAIN THEME COMPOSABLE
@Composable
fun HoomanAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    CompositionLocalProvider(LocalAppColors provides colors) {
        // You can also provide this to the standard MaterialTheme if you want
        // to use components like Button, Card, etc. with your colors.
        MaterialTheme(
            // Example of mapping your colors to MaterialTheme's color scheme
            colorScheme = MaterialTheme.colorScheme.copy(
                primary = colors.brand,
                background = colors.backgroundPrimary,
                surface = colors.backgroundSecondary,
                onPrimary = colors.textInverse,
                onBackground = colors.textPrimary,
                onSurface = colors.textPrimary,
                error = colors.error
            )
        ) {
            content()
        }
    }
}