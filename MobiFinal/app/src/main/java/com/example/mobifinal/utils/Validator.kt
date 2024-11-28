package com.example.mobifinal.utils

// Validator object to handle input validation for login and signup
object Validator {
    // Function to validate login input
    fun validateLoginInput(email: String, password: String): Boolean {
        // Check if email is not blank, matches email pattern, and password length is at least 6
        return email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6
    }

    // Function to validate signup input
    fun validateSignupInput(email: String, password: String, confirmPassword: String): Boolean {
        // Check if login input is valid and passwords match
        return validateLoginInput(email, password) && password == confirmPassword
    }
}