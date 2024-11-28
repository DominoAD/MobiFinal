package com.example.mobifinal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsScreen() {
    // State variables to hold the settings values
    var darkModeEnabled by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var fontSize by remember { mutableFloatStateOf(16f) }

    // Main column layout for the settings screen
    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen
            .padding(16.dp), // Padding around the column
        verticalArrangement = Arrangement.spacedBy(16.dp) // Space between elements
    ) {
        // Title text for the settings screen
        Text(text = "Settings", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        // Dark Mode Toggle
        Row(
            modifier = Modifier.fillMaxWidth(), // Fill the width of the parent
            horizontalArrangement = Arrangement.SpaceBetween, // Space between elements
            verticalAlignment = Alignment.CenterVertically // Center elements vertically
        ) {
            Text(text = "Dark Mode")
            Switch(
                checked = darkModeEnabled,
                onCheckedChange = { darkModeEnabled = it } // Update dark mode state
            )
        }

        // User Card
        Card(
            modifier = Modifier.fillMaxWidth(), // Fill the width of the parent
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Column(
                modifier = Modifier.padding(16.dp), // Padding around the column
                horizontalAlignment = Alignment.CenterHorizontally // Center elements horizontally
            ) {
                Text(
                    text = "User",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth() // Fill the width of the parent
                )
                Spacer(modifier = Modifier.height(8.dp)) // Spacer for spacing between elements
                Box(
                    modifier = Modifier
                        .size(64.dp) // Size of the box
                        .background(Color.Gray, shape = CircleShape) // Background color and shape
                ) {
                    Text(
                        text = "N",
                        fontSize = 32.sp,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center) // Center the text
                    )
                }
                Spacer(modifier = Modifier.height(8.dp)) // Spacer for spacing between elements
                Text(
                    text = "Name Here",
                    fontSize = 16.sp
                )
            }
        }

        // Notifications Toggle
        Row(
            modifier = Modifier.fillMaxWidth(), // Fill the width of the parent
            horizontalArrangement = Arrangement.SpaceBetween, // Space between elements
            verticalAlignment = Alignment.CenterVertically // Center elements vertically
        ) {
            Text(text = "Enable Notifications")
            Switch(
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it } // Update notifications state
            )
        }

        // Font Size Slider
        Column {
            Text(text = "Font Size: ${fontSize.toInt()}sp")
            Slider(
                value = fontSize,
                onValueChange = { fontSize = it }, // Update font size state
                valueRange = 12f..24f, // Range of the slider
                steps = 6 // Number of steps in the slider
            )
        }

        // Brightness Slider
        Text(text = "Adjust Brightness:", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        var sliderValue by remember { mutableFloatStateOf(0.5f) }
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it }, // Update slider value state
            modifier = Modifier.fillMaxWidth(), // Fill the width of the parent
            valueRange = 0f..1f // Range of the slider
        )
        Text(
            text = "Brightness: ${(sliderValue * 100).toInt()}%",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth() // Fill the width of the parent
        )

        // Save Button
        Button(
            onClick = { /* Save settings action */ },
            modifier = Modifier.align(Alignment.End) // Align the button to the end
        ) {
            Text(text = "Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen()
}