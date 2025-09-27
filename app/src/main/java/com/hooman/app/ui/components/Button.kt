package com.hooman.app.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star // Using a placeholder ico

// Button Variants
enum class HoomanButtonVariant {
    Primary, Secondary, Ghost, Text, Destructive
}

// Button Sizes
enum class HoomanButtonSize(val fontSize: Int, val paddingV: Int, val paddingH: Int, val minHeight: Int) {
//    Large(17, 20, 32, 56),
//    Medium(16, 16, 24, 44),
//    Small(15, 12, 20, 36)

    Large(16, 0, 0, 56),
    Medium(14, 0, 0, 44),
    Small(12, 0, 0, 36)

}

@Composable
fun HoomanButton(
    title: String,
    modifier: Modifier = Modifier,
    variant: HoomanButtonVariant = HoomanButtonVariant.Primary,
    size: HoomanButtonSize = HoomanButtonSize.Medium,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    val darkTheme = isSystemInDarkTheme()
    val haptic = LocalHapticFeedback.current
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(if (isPressed) 0.95f else 1f, label = "buttonPress")

    val background = when (variant) {
        HoomanButtonVariant.Primary -> HoomanColors.accentOrange
        HoomanButtonVariant.Secondary -> HoomanColors.veterinaryTeal
        HoomanButtonVariant.Ghost -> Color.Transparent
        HoomanButtonVariant.Text -> Color.Transparent
        HoomanButtonVariant.Destructive -> HoomanColors.errorRed
    }

    val foreground = when (variant) {
        HoomanButtonVariant.Primary,
        HoomanButtonVariant.Secondary,
        HoomanButtonVariant.Destructive -> Color.White
        HoomanButtonVariant.Ghost,
        HoomanButtonVariant.Text -> HoomanColors.accentOrange
    }

    val border = when (variant) {
        HoomanButtonVariant.Ghost -> BorderStroke(1.dp, HoomanColors.accentOrange)
        else -> null
    }

    val finalBg = if (!isEnabled) HoomanColors.disabled else background
    val finalFg = if (!isEnabled) HoomanColors.textSecondary.resolve() else foreground

    Box(
        modifier = modifier
            .scale(scale)
            .height(size.minHeight.dp)
            .fillMaxWidth()
            .background(finalBg, shape = RoundedCornerShape(12.dp))
            .then(
                if (border != null) Modifier.border(border, RoundedCornerShape(12.dp))
                else Modifier
            )
            .clickable(
                enabled = isEnabled && !isLoading,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(horizontal = size.paddingH.dp, vertical = size.paddingV.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leadingIcon != null && !isLoading) {
                leadingIcon()
                Spacer(modifier = Modifier.width(8.dp))
            }

            if (isLoading) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp),
                    color = finalFg
                )
            } else {
                Text(
                    text = title,
                    fontSize = size.fontSize.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = finalFg
                )
            }

            if (trailingIcon != null && !isLoading) {
                Spacer(modifier = Modifier.width(8.dp))
                trailingIcon()
            }
        }
    }
}

// Apple Sign-In Button
@Composable
fun AppleSignInButton(
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    val darkTheme = isSystemInDarkTheme()
    val bg = if (darkTheme) Color.White else Color.Black
    val fg = if (darkTheme) Color.Black else Color.White
    val border = if (darkTheme) Color.White.copy(alpha = 0.2f) else Color.Black.copy(alpha = 0.2f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(bg, RoundedCornerShape(16.dp))
            .border(1.dp, border, RoundedCornerShape(16.dp))
            .clickable(enabled = !isLoading) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            if (isLoading) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp),
                    color = fg
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Star, // Replace with custom Apple logo vector
                    contentDescription = null,
                    tint = fg,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                "Continue with Apple",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = fg
            )
        }
    }
}