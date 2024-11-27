package com.example.mobifinal.utils

object ErrorHandler {
    fun getErrorMessage(error: Throwable): String {
        return when (error.message) {
            "Invalid credentials" -> "Email or password is incorrect"
            "Signup failed" -> "Unable to create account. Please try again."
            else -> "An unexpected error occurred"
        }
    }
}
