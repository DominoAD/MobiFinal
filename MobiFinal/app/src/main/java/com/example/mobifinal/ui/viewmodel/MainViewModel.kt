package com.example.mobifinal.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    fun signup(
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                // Replace with actual signup logic (e.g., API call, database entry)
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    onSuccess()
                } else {
                    throw Exception("Signup failed")
                }
            } catch (e: Throwable) {
                onError(e)
            }
        }
    }
}
