package com.example.mobifinal

data class Report(
    val id: Int,
    val date: String,
    val time: String,
    val classroom: String,
    val studentName: String,
    val reason: String,
    val parentEmail: String,
    val absentStudents: Int,
    val presentStudents: Int,
    val totalStudents: Int,
    val className: String
)
data class Student(
    val name: String,
    val isPresent: Boolean,
    var reason: String = "",
    var parentEmail: String = ""
)