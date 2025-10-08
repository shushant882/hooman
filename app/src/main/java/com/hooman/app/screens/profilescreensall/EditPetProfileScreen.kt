package com.hooman.app.screens.profilescreensall



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hooman.app.models.EditPetProfileViewModel

import com.hooman.app.models.PetProfileNavigationEvent

import com.hooman.app.screens.allprofilestate.PetGender
import com.hooman.app.screens.allprofilestate.PetType
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPetProfileScreen(
    onNavigateBack: () -> Unit,
    onPickImage: () -> Unit,
    viewModel: EditPetProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collectLatest { event ->
            when (event) {
                PetProfileNavigationEvent.NavigateBack -> onNavigateBack()
                PetProfileNavigationEvent.PickImage -> onPickImage()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Pet Profile", color = Color.White) },
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

            // Reusing ProfilePictureSection from EditProfileScreen
            item {
                ProfilePictureSection(
                    profileImageUrl = uiState.profileImageUrl,
                    onChangePhotoClick = viewModel::onChangePhoto
                )
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Pet Name
            item {
                ProfileInputField(
                    label = "Pet Name",
                    value = uiState.petName,
                    onValueChange = viewModel::onPetNameChange
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Pet Type
            item { PetTypeSelection(uiState.selectedPetType, viewModel::onPetTypeSelected) }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Breed
            item {
                BreedDropdownField(
                    label = "Breed",
                    selectedValue = uiState.breed,
                    onValueChange = viewModel::onBreedChange
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Age
            item {
                ProfileInputField(
                    label = "Age",
                    value = uiState.age,
                    onValueChange = viewModel::onAgeChange,
                    trailingIcon = { Icon(Icons.Default.CalendarToday, "Calendar", tint = Color.Gray) }
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Gender
            item {
                Text(text = "Gender", color = Color.Gray, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
                PetGenderSelection(uiState.selectedGender, viewModel::onGenderSelected)
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Weight
            item {
                Text(text = "Weight", color = Color.Gray, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
                WeightInputField(
                    weight = uiState.weight,
                    unit = uiState.weightUnit,
                    onWeightChange = viewModel::onWeightChange,
                    onUnitChange = viewModel::onWeightUnitChange
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Neutered/Spayed
            item {
                Text(text = "Neutered/Spayed", color = Color.Gray, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
                NeuteredSpayedSelection(uiState.isNeutered, viewModel::onNeuteredStatusChange)
            }
            item { Spacer(modifier = Modifier.height(32.dp)) }

            // Save Details Button
            item {
                Button(
                    onClick = viewModel::onSaveDetails,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                    enabled = !uiState.isLoading
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    } else {
                        Icon(Icons.Default.Done, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Save Details", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Delete Profile Button
            item {
                TextButton(onClick = viewModel::onDeleteProfile, enabled = !uiState.isLoading) {
                    Icon(Icons.Default.Delete, contentDescription = null, tint = Color(0xFFF44336))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Delete Profile", color = Color(0xFFF44336), fontWeight = FontWeight.Bold)
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun PetTypeSelection(selectedType: PetType, onSelect: (PetType) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Pet Type", color = Color.Gray, modifier = Modifier.padding(bottom = 8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            PetTypeChip(text = "Dog", icon = Icons.Default.Pets, selected = selectedType == PetType.DOG, onClick = { onSelect(PetType.DOG) })
            PetTypeChip(text = "Cat", icon = Icons.Default.Pets, selected = selectedType == PetType.CAT, onClick = { onSelect(PetType.CAT) })
            PetTypeChip(text = "Bird", icon = Icons.Default.Pets, selected = selectedType == PetType.BIRD, onClick = { onSelect(PetType.BIRD) })
            PetTypeChip(text = "Fish", icon = Icons.Default.Pets, selected = selectedType == PetType.FISH, onClick = { onSelect(PetType.FISH) })
            PetTypeChip(text = "Other", icon = Icons.Default.Pets, selected = selectedType == PetType.OTHER, onClick = { onSelect(PetType.OTHER) })
        }
    }
}

@Composable
fun RowScope.PetTypeChip(text: String, icon: ImageVector, selected: Boolean, onClick: () -> Unit) {
    val colors = if (selected) ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
    else ButtonDefaults.buttonColors(containerColor = Color(0xFF1E1E1E))

    Button(onClick = onClick, colors = colors, shape = RoundedCornerShape(8.dp), modifier = Modifier.weight(1f), contentPadding = PaddingValues(8.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(icon, contentDescription = text, modifier = Modifier.size(20.dp))
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedDropdownField(label: String, selectedValue: String, onValueChange: (String) -> Unit) {
    val breeds = listOf("Golden Retriever", "Labrador", "Poodle", "Beagle") // Sample list
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        ProfileInputField(
            label = label,
            value = selectedValue,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.menuAnchor().fillMaxWidth(),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            // This line sets the corner radius to 16.dp

        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0xFF1E1E1E)).fillMaxWidth()
        ) {
            breeds.forEach { breed ->
                DropdownMenuItem(
                    text = { Text(breed, color = Color.White) },
                    onClick = {
                        onValueChange(breed)
                        expanded = false
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}


@Composable
fun PetGenderSelection(selectedGender: PetGender, onGenderSelected: (PetGender) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ToggleChip(
            text = "Male",
            icon = Icons.Default.Male,
            selected = selectedGender == PetGender.MALE,
            onClick = { onGenderSelected(PetGender.MALE) },
            modifier = Modifier.weight(1f)
        )
        ToggleChip(
            text = "Female",
            icon = Icons.Default.Female,
            selected = selectedGender == PetGender.FEMALE,
            onClick = { onGenderSelected(PetGender.FEMALE) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun NeuteredSpayedSelection(isSelected: Boolean, onSelectionChange: (Boolean) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ToggleChip(
            text = "Yes",
            icon = Icons.Default.Check,
            selected = isSelected,
            onClick = { onSelectionChange(true) },
            modifier = Modifier.weight(1f)
        )
        ToggleChip(
            text = "No",
            icon = Icons.Default.Close,
            selected = !isSelected,
            onClick = { onSelectionChange(false) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ToggleChip(text: String, icon: ImageVector, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val backgroundColor = if (selected) Color(0xFFE91E63) else Color(0xFF1E1E1E)
    val contentColor = if (selected) Color.White else Color.Gray

    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = text, tint = contentColor)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, color = contentColor, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun WeightInputField(
    weight: String,
    unit: String,
    onWeightChange: (String) -> Unit,
    onUnitChange: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ProfileInputField(
            label = "Unit",
            value = unit,
            onValueChange = onUnitChange,
            modifier = Modifier.width(100.dp)
        )
        ProfileInputField(
            label = "Weight",
            value = weight,
            onValueChange = onWeightChange,
            modifier = Modifier.weight(1f),
            keyboardType = KeyboardType.Number
        )
    }
}