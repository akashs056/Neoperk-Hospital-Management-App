package com.example.neoperkhospitalmanagementapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.neoperkhospitalmanagementapp.ui.theme.fontFamily
import com.example.neoperkhospitalmanagementapp.utils.generateRandomColor

@Composable
fun CircularInitialBox(
    initial: String,  // The character to display inside the circle
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(generateRandomColor()),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial,
            color = Color.White,
            fontFamily = fontFamily
        )
    }
}