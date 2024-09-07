package com.example.neoperkhospitalmanagementapp.ui.theme.homepage

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.neoperkhospitalmanagementapp.ui.theme.DeepBlue
import com.example.neoperkhospitalmanagementapp.ui.theme.SoftGreen
import com.example.neoperkhospitalmanagementapp.ui.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var hospitalId by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current // Get the context

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DeepBlue,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        text = "Hospital Management App",
                        fontFamily = fontFamily
                    )
                }
            )
        },
        content = {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = hospitalId,
                    onValueChange = {
                        hospitalId = it
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = DeepBlue,
                        unfocusedBorderColor = DeepBlue
                    ),
                    label = {
                        Text(
                            text = "Enter Hospital Id",
                            color = DeepBlue,
                            fontSize = 15.sp,
                            fontFamily = fontFamily
                        )
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    onClick = {
                        // handle empty conditions
                        if (hospitalId.isNotEmpty()) {
                            navController.navigate("patients_list/$hospitalId")
                        } else {
                            Toast.makeText(
                                context,
                                "Invalid hospital Id parameter",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    colors = ButtonColors(
                        containerColor = SoftGreen,
                        contentColor = Color.White,
                        disabledContentColor = Color.White,
                        disabledContainerColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    // Submit Button
                    Text(
                        text = "Submit",
                        fontSize = 16.sp,
                        fontFamily = fontFamily
                    )
                }
            }
        }
    )
}

