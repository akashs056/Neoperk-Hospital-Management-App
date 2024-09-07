package com.example.neoperkhospitalmanagementapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neoperkhospitalmanagementapp.data_models.Patient
import com.example.neoperkhospitalmanagementapp.ui.theme.LightGray
import com.example.neoperkhospitalmanagementapp.ui.theme.fontFamily
import com.example.neoperkhospitalmanagementapp.utils.getStatusColor

@Composable
fun PatientItem(
    modifier: Modifier = Modifier,
    patient: Patient,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(65.dp)
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable(onClick = onClick)
            .border(width = 0.08.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .background(LightGray)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            CircularInitialBox(initial = patient.name.take(1))
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = patient.name.take(12),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                maxLines = 1,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                fontFamily = fontFamily
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(getStatusColor(patient.status))
                    .padding(
                        horizontal = 10.dp,
                        vertical = 4.dp
                    )
            ) {
                Text(
                    text = (if (patient.status == "Under Observation") "On Watch" else patient.status),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = fontFamily
                )
            }
        }
    }
}

@Preview
@Composable
private fun PatientItemPreview() {
    PatientItem(
        patient = Patient(
            patientId = "230",
            name = "Kalam",
            status = "Admited",
            guardian = "Rahul Das",
            location = "Mumbai",
        ),
        onClick = {}
    )
}