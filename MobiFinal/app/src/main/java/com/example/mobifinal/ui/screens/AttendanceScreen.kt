package com.example.mobifinal.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobifinal.data.MyDatabaseHelper
import com.example.mobifinal.ui.components.BottomNavigationBar

data class Student(val name: String, var isPresent: Boolean, val classroom: String, var reason: String = "", var parentEmail: String = "")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen(
    navController: NavController,
    databaseHelper: MyDatabaseHelper
) {
    val context = LocalContext.current

    // Fetch classrooms dynamically from the database
    val classrooms = remember { databaseHelper.getClassrooms() }
    var selectedClassroom by remember { mutableStateOf(if (classrooms.isNotEmpty()) classrooms[0] else "") }

    // Fetch students dynamically based on the selected classroom
    var students by remember {
        mutableStateOf(
            if (selectedClassroom.isNotEmpty())
                databaseHelper.getStudentsByClassroom(selectedClassroom)
            else emptyList()
        )
    }

    var isReportScreenVisible by remember { mutableStateOf(false) }
    var absentReport by remember { mutableStateOf(emptyList<Student>()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Take Attendance",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
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
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        if (isReportScreenVisible) {
            AbsenceReport(
                absentReport = absentReport,
                selectedClassroom = selectedClassroom
            ) {
                isReportScreenVisible = false
            }
        } else {
            // Your existing attendance content here
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Classroom Selector
                Text(
                    text = "Select Classroom",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                DropdownMenu(selectedItem = selectedClassroom, items = classrooms) {
                    selectedClassroom = it
                    students = databaseHelper.getStudentsByClassroom(it)
                    students = databaseHelper.getStudentsByClassroom(selectedClassroom)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Attendance Details
                students.forEachIndexed { index, student ->
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = student.isPresent,
                                onCheckedChange = { isChecked ->
                                    students = students.toMutableList().apply {
                                        this[index] = this[index].copy(
                                            isPresent = isChecked,
                                            reason = if (isChecked) "" else this[index].reason
                                        )
                                    }
                                }
                            )
                            Text(
                                text = student.name,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        if (!student.isPresent) {
                            TextField(
                                value = student.reason,
                                onValueChange = { reason ->
                                    students = students.toMutableList().apply {
                                        this[index] = this[index].copy(reason = reason)
                                    }
                                },
                                placeholder = { Text("Reason for absence") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 32.dp, end = 8.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Complete Button
                Button(
                    onClick = {
                        absentReport = students.filter { !it.isPresent }
                        isReportScreenVisible = true
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text("Complete", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbsenceReport(absentReport: List<Student>, selectedClassroom: String, onBack: () -> Unit) {
    val context = LocalContext.current
    val updatedReport = remember { mutableStateListOf(*absentReport.toTypedArray()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Absent Report", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (updatedReport.isEmpty()) {
                    Text(
                        text = "All students are present in class!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Green,
                        modifier = Modifier.padding(vertical = 16.dp),
                        textAlign = TextAlign.Center
                    )
                } else {
                    Text(
                        text = "Absent Students in ${absentReport.firstOrNull()?.classroom ?: "Unknown Classroom"}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                    updatedReport.forEachIndexed { index, student ->
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "${student.name}: ${student.reason.ifBlank { "No reason provided" }}",
                                fontSize = 18.sp,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                            TextField(
                                value = student.parentEmail,
                                onValueChange = { email ->
                                    updatedReport[index] = updatedReport[index].copy(parentEmail = email)
                                },
                                placeholder = { Text("Enter parent's email") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            val dbHelper = MyDatabaseHelper(context)
                            updatedReport.forEach { student ->
                                dbHelper.saveReport(
                                    date = "2024-12-04",
                                    time = "14:30",
                                    classroom = student.classroom,
                                    studentName = student.name,
                                    reason = student.reason,
                                    parentEmail = student.parentEmail
                                )
                            }
                            sendEmail(context, updatedReport, )

                            onBack()


                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                    ) {
                        Text("Send Report to Parents", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    )
}

fun sendEmail(context: Context, absentReport: List<Student>, ) {
    val message = buildString {
        append("Absent Report\n")
        append("Classroom\n")
        absentReport.forEach {
            append("${it.name}: ${it.reason.ifBlank { "No reason provided" }}\n")
            append("Parent Email: ${it.parentEmail.ifBlank { "No email provided" }}\n")

        }
    }
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

@Composable
fun DropdownMenu(
    selectedItem: String,
    items: List<String>,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { expanded = true }) {
            Text(selectedItem)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    },
                    text = { Text(item) }
                )
            }
        }
    }
}
