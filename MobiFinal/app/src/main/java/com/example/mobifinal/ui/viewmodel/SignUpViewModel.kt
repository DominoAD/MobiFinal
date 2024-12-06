package com.example.mobifinal.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobifinal.data.MyDatabaseHelper
import kotlinx.coroutines.launch

class SignUpViewModel(private val databaseHelper: MyDatabaseHelper) : ViewModel() {
    private val _signUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> = _signUpState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun signUp(email: String, password: String, confirmPassword: String, username: String) {
        if (!validateInput(email, password, confirmPassword, username)) {
            return
        }

        viewModelScope.launch {
            try {
                _isLoading.value = true
                databaseHelper.createUser(email, password)
                _signUpState.value = SignUpState.Success
            } catch (e: Exception) {
                _signUpState.value = SignUpState.Error(e.message ?: "Sign up failed")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun validateInput(email: String, password: String, confirmPassword: String, username: String): Boolean {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || username.isEmpty()) {
            _signUpState.value = SignUpState.Error("All fields are required")
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _signUpState.value = SignUpState.Error("Invalid email format")
            return false
        }

        if (password.length < 6) {
            _signUpState.value = SignUpState.Error("Password must be at least 6 characters")
            return false
        }

        if (password != confirmPassword) {
            _signUpState.value = SignUpState.Error("Passwords do not match")
            return false
        }

        return true
    }
}

sealed class SignUpState {
    object Initial : SignUpState()
    object Success : SignUpState()
    data class Error(val message: String) : SignUpState()
}