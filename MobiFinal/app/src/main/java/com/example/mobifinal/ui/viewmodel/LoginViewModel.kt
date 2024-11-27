package com.example.mobifinal.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    fun login(
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                // Replace with actual login logic (e.g., API call, database query)
                if (email == "test@example.com" && password == "password123") {
                    onSuccess()
                } else {
                    throw Exception("Invalid credentials")
                }
            } catch (e: Throwable) {
                onError(e)
            }
        }
    }
}
