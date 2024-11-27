package com.example.mobifinal.utils

object Validator {
    fun validateLoginInput(email: String, password: String): Boolean {
        return email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6
    }

    fun validateSignupInput(email: String, password: String, confirmPassword: String): Boolean {
        return validateLoginInput(email, password) && password == confirmPassword
    }
}
