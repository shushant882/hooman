package com.hooman.app.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hooman.app.ui.theme.HoomanColors
import com.hooman.app.ui.theme.Urbanist
import com.hooman.app.viewmodels.EmailSignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailSignUpScreen(viewModel: EmailSignUpViewModel) {
    // State variables for the input fields and checkbox
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var verifyPassword by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var verifyPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var agreedToTerms by rememberSaveable { mutableStateOf(false) }

    val darkTheme = isSystemInDarkTheme()
    val background = if (darkTheme) HoomanColors.bgPrimary.dark else HoomanColors.bgPrimary.light
    val textPrimary = if (darkTheme) HoomanColors.textPrimary.dark else HoomanColors.textPrimary.light
    val textSecondary = if (darkTheme) HoomanColors.textSecondary.dark else HoomanColors.textSecondary.light
    val textInverse = if (darkTheme) HoomanColors.textInverse.light else HoomanColors.textInverse.dark
    val buttonBackground = HoomanColors.accentOrange

    Scaffold(
        containerColor = background,
        topBar = {
            TopAppBar(
                title = { Text("Sign Up with Email", fontFamily = Urbanist, color = textPrimary) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onNavigateBackClicked() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = textPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = background
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .fillMaxSize()
        ) {
            // EMAIL ADDRESS FIELD
            AuthTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email Address",
                placeholder = "name@email.com",
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(16.dp))

            // PASSWORD FIELD
            AuthTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                labelExtra = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Password Info",
                        tint = textSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                },
                placeholder = "********",
                keyboardType = KeyboardType.Password,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = "Toggle password visibility", tint = textSecondary)
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // VERIFY PASSWORD FIELD
            AuthTextField(
                value = verifyPassword,
                onValueChange = { verifyPassword = it },
                label = "Verify Password",
                placeholder = "********",
                keyboardType = KeyboardType.Password,
                visualTransformation = if (verifyPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (verifyPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { verifyPasswordVisible = !verifyPasswordVisible }) {
                        Icon(imageVector = image, contentDescription = "Toggle password visibility", tint = textSecondary)
                    }
                }
            )
            Spacer(modifier = Modifier.height(24.dp))

            // TERMS OF USE CHECKBOX AND TEXT
            TermsAndConditionsCheckbox(
                checked = agreedToTerms,
                onCheckedChange = { agreedToTerms = it },
                onClick = { viewModel.onTermsOfUseClicked() },
                onSecondClick = { viewModel.onPrivacyPolicyClicked() }
            )

            // Spacer to push the button to the bottom
            Spacer(modifier = Modifier.weight(1f))

            // SIGN UP BUTTON
            Button(
                onClick = { viewModel.onSignUpSuccess() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(50), // Fully rounded corners
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonBackground,
                    contentColor = textInverse
                )
            ) {
                Text(text = "SIGN UP", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

/**
 * A reusable, styled text field for authentication forms.
 */
@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    labelExtra: @Composable (() -> Unit)? = null
) {
    val darkTheme = isSystemInDarkTheme()
    val textPrimary = if (darkTheme) HoomanColors.textPrimary.dark else HoomanColors.textPrimary.light
    val textSecondary = if (darkTheme) HoomanColors.textSecondary.dark else HoomanColors.textSecondary.light
    val buttonBackground = if (darkTheme) Color(0xFF2A2A2A) else Color.White

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = label, color = textSecondary, fontFamily = Urbanist, fontSize = 14.sp)
            if (labelExtra != null) {
                Spacer(modifier = Modifier.width(4.dp))
                labelExtra()
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = placeholder, color = textSecondary) },
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = textPrimary,
                unfocusedTextColor = textPrimary,
                cursorColor = textPrimary,
                unfocusedContainerColor = buttonBackground,
                focusedContainerColor = buttonBackground.copy(alpha = 0.5f)
            )
        )
    }
}

/**
 * A composable for the checkbox and the clickable "Terms of Use" and "Privacy Policy" text.
 */
@Composable
fun TermsAndConditionsCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClick: () -> Unit,
    onSecondClick: (() -> Unit)
) {
    val darkTheme = isSystemInDarkTheme()
    val background = if (darkTheme) HoomanColors.bgPrimary.dark else HoomanColors.bgPrimary.light
    val textSecondary = if (darkTheme) HoomanColors.textSecondary.dark else HoomanColors.textSecondary.light
    val link = if (darkTheme) HoomanColors.accentOrange else HoomanColors.accentOrangeLight

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = link,
                uncheckedColor = textSecondary,
                checkmarkColor = background
            )
        )
        Spacer(modifier = Modifier.width(8.dp))

        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = textSecondary)) {
                append("I agree Hooman's ")
            }

            pushLink(
                link = LinkAnnotation.Clickable(
                    tag = "TERMS",
                    linkInteractionListener = { onClick() }
                )
            )
            withStyle(style = SpanStyle(color = link, fontWeight = FontWeight.Bold)) {
                append("Terms of Use")
            }
            pop()

            withStyle(style = SpanStyle(color = textSecondary)) {
                append(" and its ")
            }

            pushLink(
                link = LinkAnnotation.Clickable(
                    tag = "PRIVACY",
                    linkInteractionListener = { onSecondClick() }
                )
            )
            withStyle(style = SpanStyle(color = link, fontWeight = FontWeight.Bold)) {
                append("Privacy Policy")
            }
            pop()
        }

        Text(text = annotatedString, fontFamily = Urbanist)
    }
}
