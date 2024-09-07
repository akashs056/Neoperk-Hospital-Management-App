package com.example.neoperkhospitalmanagementapp.ui.theme.patient_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.neoperkhospitalmanagementapp.components.PatientItem
import com.example.neoperkhospitalmanagementapp.components.SearchBar
import com.example.neoperkhospitalmanagementapp.ui.theme.DeepBlue
import com.example.neoperkhospitalmanagementapp.ui.theme.LightBlue
import com.example.neoperkhospitalmanagementapp.ui.theme.SoftGreen
import com.example.neoperkhospitalmanagementapp.ui.theme.fontFamily
import com.example.neoperkhospitalmanagementapp.utils.filterPatients
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientsListScreen(
    hospitalId: String,
    navController: NavController,
) {

    val viewModel: PatientsViewModel = viewModel()
    val state by rememberUpdatedState(viewModel)
    val scrollState = rememberLazyListState()

    // Fetch patients when the screen loads
    LaunchedEffect(hospitalId) {
        viewModel.fetch(hospitalId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DeepBlue,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Hospital Management App", fontFamily = fontFamily)
                },
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
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 12.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Search and Filter UI
                Box(modifier = Modifier.weight(1f)) {
                    SearchBar(
                        text = state.searchQuery,
                        hint = "Search by ${state.selectedFilter}",
                        onTextChange = {
                            state.onSearchQueryChanged(it)
                        },
                        onSearchClicked = {
                            state.filteredList = filterPatients(
                                state.patientList,
                                state.searchQuery,
                                state.selectedFilter
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Box {
                    Button(
                        onClick = { state.expanded = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SoftGreen,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(45.dp)
                    ) {
                        Text(
                            "Filter: ${state.selectedFilter}",
                            fontSize = 16.sp,
                            fontFamily = fontFamily
                        )
                    }
                    DropdownMenu(
                        modifier = Modifier.background(LightBlue),
                        expanded = state.expanded,
                        onDismissRequest = { state.expanded = false }
                    ) {
                        DropdownMenuItem(onClick = {
                            state.onFilterChanged("Name")
                        }, text = { Text("Name", fontSize = 16.sp, fontFamily = fontFamily) })

                        DropdownMenuItem(onClick = {
                            state.onFilterChanged("Status")
                        }, text = { Text("Status", fontSize = 16.sp, fontFamily = fontFamily) })

                        DropdownMenuItem(onClick = {
                            state.onFilterChanged("Location")
                        }, text = { Text("Location", fontSize = 16.sp, fontFamily = fontFamily) })

                        DropdownMenuItem(onClick = {
                            state.onFilterChanged("Guardian")
                        }, text = { Text("Guardian", fontSize = 16.sp, fontFamily = fontFamily) })
                    }
                }
            }

            // Show error if there's one
            state.errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp,
                    fontFamily = fontFamily
                )
            }

            if (state.filteredList.isEmpty() && !state.isLoading && state.errorMessage == null) {
                Text(
                    text = "No Patients Available",
                    color = SoftGreen,
                    fontSize = 24.sp,
                    fontFamily = fontFamily,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Show loader if data is loading
            if (state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = DeepBlue
                    ) // Show loader
                }
            } else {
                // Show the list of patients based on filter
                SwipeRefresh(
                    state = rememberSwipeRefreshState(state.isRefreshing),
                    onRefresh = {
                        state.onRefresh(hospitalId)
                    }
                ) {
                    LazyColumn(
                        state = scrollState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            if (state.searchQuery.isEmpty()) state.patientList else
                                state.filteredList
                        ) { patient ->
                            state.errorMessage = null
                            PatientItem(patient = patient) {
                                val patientJson = Json.encodeToString(patient)
                                // Navigate to PatientDetailScreen
                                navController.navigate("patient_detail/$patientJson")
                            }
                        }
                    }
                }
            }
        }
    }
}
