package com.example.neoperkhospitalmanagementapp.utils

import androidx.compose.ui.graphics.Color
import com.example.neoperkhospitalmanagementapp.data_models.Patient

fun generateRandomColor(): Color {
    val colorOptions = listOf(
        Color(0xFF4CAF50), // Soft Green
        Color(0xFF8F8F8F), // Light Gray
        Color(0xFF003366), // Deep Blue
        Color(0xFFFF9800), // Orange
        Color(0xFF80E8F7), // Mint Green
        Color(0xFFE783F5), // Light Lavender
        Color(0xFF8FDAFC)  // Light Blue
    )
    return colorOptions.random()  // Randomly select one of the predefined colors
}

// Function to determine the color based on the status
fun getStatusColor(status: String): Color {
    return when (status.lowercase()) {
        "admitted", "critical" -> Color(0xFFF53A4D)  // Red for Admitted and Critical
        "discharged", "recovered" -> Color(0xFF63D367)  // Green for Discharged and Recovered
        else -> Color.LightGray  // Light Gray for other statuses
    }
}

// Helper function to filter patients based on selected criteria
fun filterPatients(
    patientList: List<Patient>,
    query: String,
    filterType: String
): List<Patient> {
    return if (query.isEmpty()) {
        patientList
    } else {
        when (filterType) {
            "Name" -> patientList.filter { it.name.contains(query, ignoreCase = true) }
            "Status" -> patientList.filter { it.status.contains(query, ignoreCase = true) }
            "Location" -> patientList.filter { it.location.contains(query, ignoreCase = true) }
            "Guardian" -> patientList.filter { it.guardian.contains(query, ignoreCase = true) }
            else -> patientList
        }
    }
}
