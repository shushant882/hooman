package com.hooman.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hooman.app.ui.main.MainViewModel

@Composable
fun ProfileScreen(){
    LazyColumn(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(16.dp)){
item {
    Spacer(modifier= Modifier.height(16.dp))
    Text(
        text="My Profile",
        style = MaterialTheme.typography.bodyMedium, fontFamily =
    )
}

    }

}
