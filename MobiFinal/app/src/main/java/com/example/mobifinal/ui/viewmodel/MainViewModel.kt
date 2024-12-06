package com.example.mobifinal.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        _message.value = "Welcome to the Main Screen!"
    }

    fun updateMessage(newMessage: String) {
        _message.value = newMessage
    }
}