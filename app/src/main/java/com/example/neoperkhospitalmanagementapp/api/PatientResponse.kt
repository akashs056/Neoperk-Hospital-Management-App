package com.example.neoperkhospitalmanagementapp.api

import com.example.neoperkhospitalmanagementapp.data_models.Patient
import kotlinx.serialization.Serializable

@Serializable
data class PatientResponse(
    val patients: List<Patient>
)
