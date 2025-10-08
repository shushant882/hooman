package com.hooman.app.screens.profilescreensall

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hooman.app.screens.allprofilestate.Pet
import com.hooman.app.screens.allprofilestate.User
import com.hooman.app.navigation.AppRoutes
import com.hooman.app.models.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest

// Define your app's navigation routes for maintainability


@Composable
fun ProfileScreen(
    // A function passed from your NavHost to handle navigation
    onNavigate: (String) -> Unit,
    viewModel: ProfileViewModel = viewModel()
) {
    // Observe the state from the ViewModel
    val state by viewModel.uiState.collectAsState()

    // Listen for navigation events from the ViewModel
    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collectLatest { route ->
            onNavigate(route)
        }
    }

    Surface(color = Color(0xFF121212), modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            // -- Header --
            item {
                Text(
                    text = "My Profile",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    modifier = Modifier.padding(start=10.dp,top=39.dp)
                )
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }

            // -- User Profile Section --
            item { UserProfileHeader(state.user) { viewModel.onNavigate(AppRoutes.EDIT_PROFILE) } }
            item { Spacer(modifier = Modifier.height(24.dp)) }

            // -- My Pet Section --
            item {
                SectionHeader(
                    title = "My Pet",
                    actionText = "Manage Pets"
                ) { viewModel.onNavigate(AppRoutes.MANAGE_PETS) }
            }
            item {
                MyPetCard(state.pet) { viewModel.onNavigate(AppRoutes.EDIT_PET) }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }

            // -- Pet Details Section --
            item { SectionHeader(title = "Pet Details") }
            item {
                SettingsGroup {
                    SettingsItem(
                        icon = Icons.Default.FavoriteBorder,
                        title = "Health & Care"
                    ) { viewModel.onNavigate(AppRoutes.HEALTH_CARE) }
                    SettingsItem(
                        icon = Icons.Default.Spa,
                        title = "Lifestyle & Preferences"
                    ) { viewModel.onNavigate(AppRoutes.LIFESTYLE_PREFERENCES) }
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }

            // -- App Settings Section --
            item { SectionHeader(title = "App Settings") }
            item {
                SettingsGroup {
                    SettingsItem(
                        icon = Icons.Default.Notifications,
                        title = "Notifications"
                    ) { viewModel.onNavigate(AppRoutes.NOTIFICATIONS) }
                    SettingsItem(
                        icon = Icons.Default.CheckCircleOutline,
                        title = "Units"
                    ) { viewModel.onNavigate(AppRoutes.UNITS) }
                    SettingsItem(
                        icon = Icons.Default.Language,
                        title = "Language",
                        value = "English"
                    ) { viewModel.onNavigate(AppRoutes.LANGUAGE) }
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }


            // -- Hooman+ Section --
            item { SectionHeader(title = "Hooman+") }
            item { PremiumPlanCard { viewModel.onNavigate(AppRoutes.MY_PLAN) } }
            item {
                SettingsGroup {
                    SettingsItem(
                        icon = Icons.AutoMirrored.Filled.ReceiptLong,
                        title = "Billing & Payments"
                    ) { viewModel.onNavigate(AppRoutes.BILLING) }
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }

            // -- Support & Feedback Section --
            item { SectionHeader(title = "Support & Feedback") }
            item {
                SettingsGroup {
                    SettingsItem(
                        icon = Icons.AutoMirrored.Filled.HelpOutline,
                        title = "FAQ"
                    ) { viewModel.onNavigate(AppRoutes.FAQ) }
                    SettingsItem(
                        icon = Icons.Default.HeadsetMic,
                        title = "Contact Support"
                    ) { viewModel.onNavigate(AppRoutes.CONTACT_SUPPORT) }
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }

            // -- Legal Section --
            item { SectionHeader(title = "Legal") }
            item {
                SettingsGroup {
                    SettingsItem(
                        icon = Icons.AutoMirrored.Filled.ListAlt,
                        title = "Terms of Service"
                    ) { viewModel.onNavigate(AppRoutes.TERMS_OF_SERVICE) }
                    SettingsItem(
                        icon = Icons.Default.PrivacyTip,
                        title = "Privacy Policy"
                    ) { viewModel.onNavigate(AppRoutes.PRIVACY_POLICY) }
                }
            }
            item { Spacer(modifier = Modifier.height(32.dp)) }

            // -- Logout Button --
            item {
                Button(
                    onClick = { viewModel.onLogout() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2F2F2F))
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Logout",
                        tint = Color(0xFFF44336)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Logout", color = Color(0xFFF44336), fontWeight = FontWeight.Bold)
                }
            }

            // -- Footer --
            item {
                Text(
                    text = "Made with ♥ for Pet Parents\nV1",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }
        }
    }
}

// Reusable component for section titles
@Composable
fun SectionHeader(title: String, actionText: String? = null, onActionClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, color = Color.Gray, fontWeight = FontWeight.Bold)
        if (actionText != null && onActionClick != null) {
            Text(
                text = actionText,
                color = Color(0xFFE91E63),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.clickable(onClick = onActionClick)
            )
        }
    }
}

// Reusable container for a group of settings items
@Composable
fun SettingsGroup(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF1E1E1E))
    ) {
        content()
    }
}

// User profile header composable
@Composable
fun UserProfileHeader(user: User, onEditProfileClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE91E63)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = user.initials,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .padding(4.dp)
                    .align(Alignment.BottomEnd)
            )
        }
        Spacer(Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(user.name, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(user.handle, color = Color.Gray, fontSize = 16.sp)
        }
        Text(
            text = "Edit Profile >",
            color = Color(0xFFE91E63),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable(onClick = onEditProfileClick)
        )
    }
}

// Pet card composable
@Composable
fun MyPetCard(pet: Pet, onEditClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Replace with your actual image resource
            Image(
                // painter = painterResource(id = R.drawable.buddy_dog),
                imageVector = Icons.Default.Pets, // Placeholder
                contentDescription = pet.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(pet.name, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(pet.details, color = Color.Gray, fontSize = 14.sp)
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 4.dp)) {
                    Icon(Icons.Default.Male, contentDescription = "Gender", tint = Color.Gray, modifier = Modifier.size(16.dp))
                    Text(pet.gender, color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(start = 4.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = pet.category,
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .background(Color(0xFFE65100), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
            IconButton(onClick = onEditClick) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Pet", tint = Color.Gray)
            }
        }
    }
}

@Composable
fun PremiumPlanCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF4A2A2A))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.WorkspacePremium, contentDescription = "Premium", tint = Color(0xFFFFC107))
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("My Plan", color = Color.White, fontWeight = FontWeight.Bold)
                Text("Next billing date: August 15, 2023", color = Color.Gray, fontSize = 12.sp)
            }
            Text(
                "Premium",
                color = Color(0xFFFFC107),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color(0x33FFC107), RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

// Reusable component for a single setting item row
@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    value: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title, tint = Color.Gray)
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, color = Color.White, modifier = Modifier.weight(1f))
        if (value != null) {
            Text(value, color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(8.dp))
        }
        Icon(
            Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(16.dp)
        )
    }
}


@Composable
fun ProfileInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    readOnly: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.Gray) },
        modifier = modifier,
        readOnly = readOnly,
        trailingIcon = trailingIcon,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFE91E63),
            unfocusedBorderColor = Color.DarkGray,
            cursorColor = Color(0xFFE91E63),
            focusedLabelColor = Color(0xFFE91E63),
            unfocusedLabelColor = Color.Gray,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color(0xFF1E1E1E),
            unfocusedContainerColor = Color(0xFF1E1E1E),
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = singleLine,
        shape = RoundedCornerShape(12.dp)
    )
}

