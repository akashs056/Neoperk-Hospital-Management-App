package com.example.neoperkhospitalmanagementapp.ui.theme.patient_detail_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.neoperkhospitalmanagementapp.components.PatientDetailCard
import com.example.neoperkhospitalmanagementapp.data_models.Patient
import com.example.neoperkhospitalmanagementapp.ui.theme.DeepBlue
import com.example.neoperkhospitalmanagementapp.ui.theme.fontFamily
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDetailScreen(patientJson: String, navController: NavController) {
    val patient = Json.decodeFromString<Patient>(patientJson)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Patient Details", fontFamily = fontFamily) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DeepBlue,
                    titleContentColor = Color.White,
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp() // Handle back navigation
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        // Patient Details in a card format
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PatientDetailCard(patient = patient)
        }
    }
}
