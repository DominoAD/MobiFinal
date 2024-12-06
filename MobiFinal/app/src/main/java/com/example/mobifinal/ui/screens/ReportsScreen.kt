package com.example.mobifinal.ui.screens

import CommonBottomNavigation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mobifinal.Report
import com.example.mobifinal.data.MyDatabaseHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(databaseHelper: MyDatabaseHelper, navController: NavHostController) {
    var reports by remember { mutableStateOf(emptyList<Report>()) }

    LaunchedEffect(Unit) {
        reports = databaseHelper.getReports()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reports") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            CommonBottomNavigation(
                currentRoute = "reports",
                onNavigateToHome = { navController.navigate("home") },
                onNavigateToAttendance = { navController.navigate("attendance") },
                onNavigateToReports = { /* Already on reports screen */ },
                onNavigateToSettings = { navController.navigate("settings") }
            )
        }
    ) { paddingValues ->
        if (reports.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No reports available.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(reports) { report ->
                    ReportCard(report)
                }
            }
        }
    }
}

@Composable
private fun ReportCard(report: Report) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Class: ${report.className}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Date: ${report.date}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Total Students: ${report.totalStudents}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Present: ${report.presentStudents}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Green
            )
            Text(
                text = "Absent: ${report.absentStudents}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Red
            )
            LinearProgressIndicator(
                progress = report.presentStudents.toFloat() / report.totalStudents.toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = Color.Green,
                trackColor = Color.Red
            )
        }
    }
}