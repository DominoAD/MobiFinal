package com.example.mobifinal.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobifinal.data.MyDatabaseHelper
import kotlinx.coroutines.launch

class LoginViewModel(private val databaseHelper: MyDatabaseHelper) : ViewModel() {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val userExists = databaseHelper.getUser(email, password)
                if (userExists) {
                    _loginState.value = LoginState.Success
                } else {
                    _loginState.value = LoginState.Error("Invalid email or password")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Login failed")
            } finally {
                _isLoading.value = false
            }
        }
    }
}

sealed class LoginState {
    object Initial : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}