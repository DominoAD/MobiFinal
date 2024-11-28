package com.example.mobifinal.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    // Function to handle login logic
    fun login(
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        // Launch a coroutine in the ViewModel scope
        viewModelScope.launch {
            try {
                // Replace with actual login logic (e.g., API call, database query)
                if (email == "test@example.com" && password == "password123") {
                    // Call onSuccess callback if login is successful
                    onSuccess()
                } else {
                    // Throw an exception if credentials are invalid
                    throw Exception("Invalid credentials")
                }
            } catch (e: Throwable) {
                // Call onError callback if an error occurs
                onError(e)
            }
        }
    }
}