package com.example.mobifinal.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast

data class Student(val name: String, var isPresent: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen() {
    // Get the current context
    val context = LocalContext.current

    // Remember the list of students and their attendance status
    var students by remember { mutableStateOf(listOf(
        Student("John Doe", false),
        Student("Jane Smith", false),
        Student("Alice Johnson", false)
    )) }

    Scaffold(
        topBar = {
            // Top app bar with title
            TopAppBar(
                title = { Text(text = "Attendance", fontSize = 20.sp) },
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
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Section
                Text(
                    text = "Attendance Details",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Attendance Checklist
                students.forEachIndexed { index, student ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Checkbox for each student to mark attendance
                        Checkbox(
                            checked = student.isPresent,
                            onCheckedChange = { isChecked ->
                                // Update the attendance status of the student
                                students = students.toMutableList().apply {
                                    this[index] = this[index].copy(isPresent = isChecked)
                                }
                            }
                        )
                        // Display the student's name
                        Text(
                            text = student.name,
                            fontSize = 18.sp,
                            color = Color(0xFF333333),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Button to send the attendance report
                Button(
                    onClick = { sendAttendanceReport(context, students) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text(text = "Send Attendance Report", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    )
}

fun sendAttendanceReport(context: Context, students: List<Student>) {
    // Filter the list of students to get those who are present and absent
    val presentStudents = students.filter { it.isPresent }
    val absentStudents = students.filter { !it.isPresent }

    // Build a message string with the attendance details
    val message = buildString {
        append("Attendance Report Sent\n\n")
        append("Present Students: ${presentStudents.size}\n")
        presentStudents.forEach { append("- ${it.name}\n") }
        append("\nAbsent Students: ${absentStudents.size}\n")
        absentStudents.forEach { append("- ${it.name}\n") }
    }

    // Show the message in a Toast
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()

}

@Preview(showBackground = true)
@Composable
fun PreviewAttendanceScreen() {
    AttendanceScreen()
}