package com.example.neoperkhospitalmanagementapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("all_patients_fetcher")
    suspend fun fetchPatients(@Query("hospitalId") hospitalId : String): PatientResponse
}