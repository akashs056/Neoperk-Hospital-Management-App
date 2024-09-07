package com.example.neoperkhospitalmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.neoperkhospitalmanagementapp.ui.theme.homepage.HomeScreen
import com.example.neoperkhospitalmanagementapp.ui.theme.patient_detail_screen.PatientDetailScreen
import com.example.neoperkhospitalmanagementapp.ui.theme.patient_list_screen.PatientsListScreen

@Composable
fun NavGraph() {
    val navController =  rememberNavController()
    NavHost(navController = navController, startDestination = "home") {

        composable("home"){
            HomeScreen(navController = navController)
        }

        composable(
            route = "patients_list/{hospitalId}",
            arguments = listOf(navArgument("hospitalId"){
                type = NavType.StringType
            })
        ){navBackStackEntry ->
            val hospitalId = navBackStackEntry.arguments?.getString("hospitalId") ?: ""
            PatientsListScreen(hospitalId = hospitalId, navController = navController)
        }

        composable(
            route = "patient_detail/{patientJson}",
            arguments = listOf(navArgument("patientJson") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            val patientJson = navBackStackEntry.arguments?.getString("patientJson") ?: ""
            PatientDetailScreen(patientJson = patientJson,navController)
        }
    }
}