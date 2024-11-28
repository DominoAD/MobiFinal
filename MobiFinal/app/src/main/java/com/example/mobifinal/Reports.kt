package com.example.mobifinal

import com.example.mobifinal.ui.screens.Student

data class Report(
    val date: String,
    val students: List<Student>
)

data class Student(
    val name: String,
    val isPresent: Boolean
)
