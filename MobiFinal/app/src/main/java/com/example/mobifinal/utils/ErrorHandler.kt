package com.example.mobifinal.utils

// ErrorHandler object to handle error messages based on the error type
object ErrorHandler {
    // Function to get a user-friendly error message based on the error
    fun getErrorMessage(error: Throwable): String {
        return when (error.message) {
            "Invalid credentials" -> "Email or password is incorrect"
            "Signup failed" -> "Unable to create account. Please try again."
            else -> "An unexpected error occurred"
        }
    }
}