package com.hooman.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.LinkAnnotation
import com.hooman.app.R
import com.hooman.app.ui.theme.HoomanColors
import com.hooman.app.ui.theme.Urbanist
import com.hooman.app.viewmodels.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val darkTheme = isSystemInDarkTheme()
    val background = if (darkTheme) HoomanColors.bgPrimary.dark else HoomanColors.bgPrimary.light
    val textPrimary = if (darkTheme) HoomanColors.textPrimary.dark else HoomanColors.textPrimary.light
    val textSecondary = if (darkTheme) HoomanColors.textSecondary.dark else HoomanColors.textSecondary.light

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
        ) {
            // Spacer from the top of the screen
            Spacer(modifier = Modifier.height(100.dp))

            Column(
                modifier = Modifier
                    .width(210.37.dp)
                    .height(220.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // App Logo
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.hooman_logo),
                    contentDescription = "Hooman Logo",
                    modifier = Modifier.size(60.dp),
                    colorFilter = ColorFilter.tint(textPrimary)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Welcome Text
                Text(
                    text = "Welcome to Hooman",
                    color = textPrimary,
                    fontSize = 34.sp,
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Subtitle Text
                Text(
                    text = "Sign in to access your personalised pet care journey",
                    color = textSecondary,
                    fontSize = 16.sp,
                    fontFamily = Urbanist,
                    textAlign = TextAlign.Start
                )
            }

            // This spacer pushes the content below it downwards
            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // "Already have an account?" Text with a clickable "Sign In"
                ClickableTextLine(
                    regularText = "Already have an account? ",
                    clickableText = "Sign In",
                    onClick = { viewModel.onEmailSignInClicked() }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Social/Email Sign-up Buttons
                AuthButton(
                    icon = Icons.Default.Email,
                    text = "Sign up with Email",
                    onClick = { viewModel.onEmailSignUpClicked() }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // NOTE: Replace R.drawable.ic_apple_logo with your actual drawable resource
                AuthButton(
                    iconResId = R.drawable.apple_logo,
                    iconColor = if (darkTheme) Color.Unspecified else Color.Black,
                    text = "Continue with Apple",
                    onClick = { viewModel.onAppleSignInClicked() }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // NOTE: Replace R.drawable.ic_google_logo with your actual drawable resource
                AuthButton(
                    iconResId = R.drawable.google_logo,
                    text = "Continue with Google",
                    onClick = { viewModel.onGoogleSignInClicked() }
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Terms and Privacy Policy Text
                ClickableTextLine(
                    regularText = "By continuing, you agree to our ",
                    clickableText = "Terms of Service",
                    andText = " and ",
                    secondClickableText = "Privacy Policy",
                    onClick = { viewModel.onTermsOfServiceClicked() },
                    onSecondClick = { viewModel.onPrivacyPolicyClicked() }
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

/**
 * A reusable button for authentication options (Email, Apple, Google).
 */
@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconResId: Int? = null,
    iconColor: Color = Color.Unspecified,
    text: String,
    onClick: () -> Unit
) {
    val darkTheme = isSystemInDarkTheme()
    val textPrimary = if (darkTheme) HoomanColors.textPrimary.dark else HoomanColors.textPrimary.light
    val buttonBackground = if (darkTheme) Color(0xFF2A2A2A) else Color.White

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonBackground,
            contentColor = textPrimary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Show the icon
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            } else if (iconResId != null) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = iconColor
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Button text
            Text(
                text = text,
                fontFamily = Urbanist,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
    }
}

/**
 * A reusable composable for text lines containing clickable links.
 */
@Composable
fun ClickableTextLine(
    regularText: String,
    clickableText: String,
    andText: String? = null,
    secondClickableText: String? = null,
    onClick: () -> Unit,
    onSecondClick: (() -> Unit)? = null
) {
    val darkTheme = isSystemInDarkTheme()
    val textSecondary = if (darkTheme) HoomanColors.textSecondary.dark else HoomanColors.textSecondary.light
    val link = if (darkTheme) HoomanColors.accentOrange else HoomanColors.accentOrangeLight

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = textSecondary, fontSize = 14.sp)) {
            append(regularText)
        }

        pushLink(
            link = LinkAnnotation.Clickable(
                tag = "FIRST_LINK", // Tag is optional, the action is in the listener
                linkInteractionListener = { onClick() }
            )
        )
        withStyle(style = SpanStyle(color = link, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)) {
            append(clickableText)
        }
        pop() // Pops the link annotation

        if (andText != null && secondClickableText != null && onSecondClick != null) {
            withStyle(style = SpanStyle(color = textSecondary, fontSize = 14.sp)) {
                append(andText)
            }

            pushLink(
                link = LinkAnnotation.Clickable(
                    tag = "SECOND_LINK",
                    linkInteractionListener = { onSecondClick() }
                )
            )
            withStyle(style = SpanStyle(color = link, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)) {
                append(secondClickableText)
            }
            pop()
        }
    }

    Text(
        text = annotatedString,
        modifier = Modifier.fillMaxWidth(),
        fontFamily = Urbanist,
        textAlign = TextAlign.Center
    )
}
