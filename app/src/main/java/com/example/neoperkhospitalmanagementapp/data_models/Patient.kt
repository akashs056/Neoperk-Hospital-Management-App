package com.example.neoperkhospitalmanagementapp.data_models

import kotlinx.serialization.Serializable

@Serializable
data class Patient(
    val patientId: String? = null,
    val name: String,
    val status: String,
    val guardian: String,
    val location: String
)