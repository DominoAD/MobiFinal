package com.example.mobifinal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobifinal.Report

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen() {
    // Remember a list of reports to display
    val reports = remember {
        listOf(
            Report(
                date = "2024-11-28",
                students = listOf(
                    Student("John Doe", true),
                    Student("Jane Smith", false),
                    Student("Alice Johnson", true)
                )
            ),
            Report(
                date = "2024-11-27",
                students = listOf(
                    Student("John Doe", false),
                    Student("Jane Smith", true),
                    Student("Alice Johnson", true)
                )
            )
        )
    }

    Scaffold(
        topBar = {
            // Top app bar with title
            TopAppBar(
                title = { Text(text = "Attendance Reports", fontSize = 20.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title of the screen
                Text(
                    text = "Attendance Reports",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // List of reports
                LazyColumn {
                    items(reports.size) { index ->
                        // Display each report in a card
                        ReportCard(report = reports[index])
                    }
                }
            }
        }
    )
}

@Composable
fun ReportCard(report: Report) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Display the date of the report
            Text(
                text = "Date: ${report.date}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6200EE)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Display the list of students and their attendance status
            report.students.forEach { student ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Display the student's name
                    Text(
                        text = student.name,
                        fontSize = 16.sp,
                        color = Color(0xFF333333)
                    )
                    // Display the student's attendance status
                    Text(
                        text = if (student.isPresent) "Present" else "Absent",
                        fontSize = 16.sp,
                        color = if (student.isPresent) Color.Green else Color.Red
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReportsScreen() {
    ReportsScreen()
}