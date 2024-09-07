package com.example.neoperkhospitalmanagementapp.ui.theme.patient_list_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neoperkhospitalmanagementapp.api.apiService
import com.example.neoperkhospitalmanagementapp.data_models.Patient
import com.example.neoperkhospitalmanagementapp.utils.filterPatients
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class PatientsViewModel : ViewModel() {
    var patientList by mutableStateOf<List<Patient>>(emptyList())
    var filteredList by mutableStateOf<List<Patient>>(emptyList())
    var isLoading by mutableStateOf(true)
    var errorMessage by mutableStateOf<String?>(null)
    var isRefreshing by mutableStateOf(false)
    var searchQuery by mutableStateOf("")
    var selectedFilter by mutableStateOf("Name")
    var expanded by mutableStateOf(false)
    private var lastFetchedId: String? = null

    fun fetch(hospitalId: String) {
        if (hospitalId == lastFetchedId && !isRefreshing) {
            // Avoid fetching if already loaded
            return
        }
        lastFetchedId = hospitalId
        viewModelScope.launch {
            try {
                val response = apiService.fetchPatients(hospitalId)
                patientList = response.patients
                filteredList = response.patients
                isLoading = false
                isRefreshing = false
            } catch (e: HttpException) {
                errorMessage = "No patients found for the specified hospitalId."
                isLoading = false
                isRefreshing = false
            } catch (e: IOException) {
                errorMessage = "Network error: Please check your internet connection"
                isLoading = false
                isRefreshing = false
            } catch (e: Exception) {
                errorMessage = "An unexpected error occurred."
                isLoading = false
                isRefreshing = false
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        searchQuery = query
        filteredList = filterPatients(patientList, searchQuery, selectedFilter)
    }

    fun onFilterChanged(filter: String) {
        selectedFilter = filter
        expanded = false
        filteredList = filterPatients(patientList, searchQuery, selectedFilter)
    }

    fun onRefresh(hospitalId: String) {
        isRefreshing = true
        searchQuery = ""
        fetch(hospitalId)
    }
}
