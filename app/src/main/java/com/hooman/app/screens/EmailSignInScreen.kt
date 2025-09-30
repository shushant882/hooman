package com.hooman.app.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hooman.app.ui.theme.HoomanColors
import com.hooman.app.ui.theme.Urbanist
import com.hooman.app.viewmodels.EmailSignInViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailSignInScreen(viewModel: EmailSignInViewModel) {
    // State variables for the input fields and checkbox
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var rememberMe by rememberSaveable { mutableStateOf(false) }

    val darkTheme = isSystemInDarkTheme()
    val background = if (darkTheme) HoomanColors.bgPrimary.dark else HoomanColors.bgPrimary.light
    val textPrimary = if (darkTheme) HoomanColors.textPrimary.dark else HoomanColors.textPrimary.light
    val textSecondary = if (darkTheme) HoomanColors.textSecondary.dark else HoomanColors.textSecondary.light
    val accent = if (darkTheme) HoomanColors.accentOrange else HoomanColors.accentOrangeLight
    val textInverse = if (darkTheme) HoomanColors.textInverse.light else HoomanColors.textInverse.dark
    val buttonBackground = HoomanColors.accentOrange

    Scaffold(
        containerColor = background,
        topBar = {
            TopAppBar(
                title = { Text("Sign In", fontFamily = Urbanist, color = textPrimary) },
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

            // REMEMBER ME & FORGOT PASSWORD ROW
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = accent,
                            uncheckedColor = textSecondary,
                            checkmarkColor = background
                        )
                    )
                    Text(text = "Remember me", color = textSecondary)
                }
                TextButton(onClick = { viewModel.onForgotPasswordClicked() }) {
                    Text("Forgot Password?", color = accent)
                }
            }

            // Spacer to push the button to the bottom
            Spacer(modifier = Modifier.weight(1f))

            // SIGN IN BUTTON
            Button(
                onClick = { viewModel.onSignInSuccess() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(50), // Fully rounded corners
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonBackground,
                    contentColor = textInverse
                )
            ) {
                Text(text = "SIGN IN", fontFamily = Urbanist, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
