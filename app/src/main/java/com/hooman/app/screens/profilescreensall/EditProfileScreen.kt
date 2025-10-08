package com.hooman.app.screens.profilescreensall // Adjust package as needed

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.hooman.app.R // Make sure you have an R.drawable.profile_placeholder for the default image

import com.hooman.app.screens.allprofilestate.Gender
import com.hooman.app.models.EditProfileNavigationEvent
import com.hooman.app.models.EditProfileViewModel

import kotlinx.coroutines.flow.collectLatest
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onPickImage: () -> Unit, // This would trigger an ActivityResultLauncher in your Activity
    viewModel: EditProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    // Observe navigation events from ViewModel
    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collectLatest { event ->
            when (event) {
                EditProfileNavigationEvent.NavigateBack -> onNavigateBack()
                EditProfileNavigationEvent.NavigateToLogin -> onNavigateToLogin()
                EditProfileNavigationEvent.PickImage -> onPickImage() // Trigger image picker
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Your Profile", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = viewModel::onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF121212))
            )
        },
        containerColor = Color(0xFF121212)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Profile Picture
            item {
                ProfilePictureSection(
                    profileImageUrl = uiState.profileImageUrl,
                    onChangePhotoClick = viewModel::onChangePhoto
                )
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }

            // User Name
            item {
                ProfileInputField(
                    label = "User Name",
                    value = uiState.username,
                    onValueChange = viewModel::onUsernameChange,
                    singleLine = true
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Full Name
            item {
                ProfileInputField(
                    label = "Full Name",
                    value = uiState.fullName,
                    onValueChange = viewModel::onFullNameChange,
                    singleLine = true
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Email
            item {
                ProfileInputField(
                    label = "Email",
                    value = uiState.email,
                    onValueChange = viewModel::onEmailChange,
                    keyboardType = KeyboardType.Email,
                    singleLine = true
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Date of Birth
            item {
                DatePickerInputField(
                    context = context,
                    label = "Date of Birth",
                    value = uiState.dateOfBirth,
                    onValueChange = viewModel::onDateOfBirthChange
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Phone Number
            item {
                PhoneNumberInputField(
                    countryCode = uiState.countryCode,
                    phoneNumber = uiState.phoneNumber,
                    onCountryCodeChange = viewModel::onCountryCodeChange,
                    onPhoneNumberChange = viewModel::onPhoneNumberChange
                )
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Gender Selection
            item {
                GenderSelection(
                    selectedGender = uiState.selectedGender,
                    onGenderSelected = viewModel::onGenderSelected
                )
            }
            item { Spacer(modifier = Modifier.height(32.dp)) }

            // Save Details Button
            item {
                Button(
                    onClick = viewModel::onSaveDetails,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                    enabled = !uiState.isLoading
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    } else {
                        Icon(Icons.Default.Done, contentDescription = "Save", tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Save Details", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Delete Profile Button
            item {
                TextButton(
                    onClick = viewModel::onDeleteProfile,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isLoading
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Profile", tint = Color(0xFFF44336))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Delete Profile", color = Color(0xFFF44336), fontWeight = FontWeight.Bold)
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

// Reusable Profile Picture Section
@Composable
fun ProfilePictureSection(profileImageUrl: String?, onChangePhotoClick: () -> Unit) {
    val painter: Painter = if (profileImageUrl != null) {
        // This line will now work correctly
        rememberAsyncImagePainter(model = profileImageUrl)
    } else {
        // Make sure you have a placeholder image at this path
        painterResource(id = R.drawable.ic_launcher_foreground)
    }

    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(Color.DarkGray)
            .clickable(onClick = onChangePhotoClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Icon(
            imageVector = Icons.Default.CameraAlt,
            contentDescription = "Change Photo",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 8.dp, y = 8.dp) // Adjust offset to position the camera icon
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFFE91E63))
                .border(2.dp, Color.Black, CircleShape)
                .padding(6.dp)
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Tap to change photo",
        color = Color.Gray,
        fontSize = 14.sp,
        modifier = Modifier.clickable(onClick = onChangePhotoClick)
    )
}

// Reusable Input Field for Profile Details
@Composable
fun ProfileInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.Gray) },
        modifier = Modifier.fillMaxWidth(),
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

// Date Picker Input Field
@Composable
fun DatePickerInputField(
    context: Context,
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            onValueChange("$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear")
        }, year, month, day
    )

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) }, // Allow manual input if desired, or make it readonly
        label = { Text(label, color = Color.Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { datePickerDialog.show() }, // Show picker on click
        readOnly = true, // Make it read-only to force picker usage
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
        trailingIcon = {
            IconButton(onClick = { datePickerDialog.show() }) {
                Icon(Icons.Default.CalendarToday, contentDescription = "Select Date", tint = Color.Gray)
            }
        },
        shape = RoundedCornerShape(12.dp)
    )
}

// Phone Number Input Field with Country Code
@Composable
fun PhoneNumberInputField(
    countryCode: String,
    phoneNumber: String,
    onCountryCodeChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Country Code Dropdown (simplified for this example, usually a complex selector)
        OutlinedTextField(
            value = countryCode,
            onValueChange = onCountryCodeChange,
            modifier = Modifier.width(100.dp), // Adjust width as needed
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
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon = {
                // Placeholder for flag - you'd integrate actual flag resources here
                // Image(painterResource(id = R.drawable.ic_nigeria_flag), contentDescription = "Flag")
                Text("🇳🇬", fontSize = 18.sp)
            },
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        // Phone Number Input
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            label = { Text("8023456789", color = Color.Gray) }, // Placeholder in label
            modifier = Modifier.weight(1f),
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )
    }
}

// Gender Selection Component
@Composable
fun GenderSelection(
    selectedGender: Gender,
    onGenderSelected: (Gender) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Gender", color = Color.Gray, modifier = Modifier.padding(bottom = 8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GenderChip(
                gender = Gender.MALE,
                selected = selectedGender == Gender.MALE,
                onClick = { onGenderSelected(Gender.MALE) },
                modifier = Modifier.weight(1f)
            )
            GenderChip(
                gender = Gender.FEMALE,
                selected = selectedGender == Gender.FEMALE,
                onClick = { onGenderSelected(Gender.FEMALE) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun GenderChip(
    gender: Gender,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (selected) Color(0xFFE91E63) else Color(0xFF1E1E1E)
    val contentColor = if (selected) Color.White else Color.Gray

    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = if (gender == Gender.MALE) Icons.Default.Male else Icons.Default.Female,
                contentDescription = gender.name,
                tint = contentColor
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = gender.name,
                color = contentColor,
                fontWeight = FontWeight.Medium
            )
        }
    }
}