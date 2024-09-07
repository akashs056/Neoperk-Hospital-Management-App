package com.example.neoperkhospitalmanagementapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neoperkhospitalmanagementapp.data_models.Patient
import com.example.neoperkhospitalmanagementapp.ui.theme.LightBlue
import com.example.neoperkhospitalmanagementapp.ui.theme.fontFamily

@Composable
fun PatientDetailCard(
    modifier: Modifier = Modifier,
    patient: Patient
) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightBlue)
                .padding(16.dp)
        ) {
            Text(
                "Name: ${patient.name}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Patient ID: ${patient.patientId}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontFamily = fontFamily
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Status: ${patient.status}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontFamily = fontFamily
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Location: ${patient.location}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontFamily = fontFamily
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Guardian: ${patient.guardian}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontFamily = fontFamily
            )
        }
    }
}