package com.example.mobifinal.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    // Function to handle signup logic
    fun signup(
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        // Launch a coroutine in the ViewModel scope
        viewModelScope.launch {
            try {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    // Call onSuccess callback if signup is successful
                    onSuccess()
                } else {
                    // Throw an exception if signup fails
                    throw Exception("Signup failed")
                }
            } catch (e: Throwable) {
                // Call onError callback if an error occurs
                onError(e)
            }
        }
    }
}