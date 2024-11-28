package com.example.mobifinal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobifinal.ui.viewmodel.MainViewModel
import com.example.mobifinal.utils.Validator
import com.example.mobifinal.utils.ErrorHandler

@Composable
fun SignupScreen(
    onSignupSuccess: () -> Unit,
    viewModel: MainViewModel
) {
    // State variables to hold email, password, confirm password, and error message
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Main column layout for the signup screen
    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen
            .padding(16.dp), // Padding around the column
        verticalArrangement = Arrangement.Center, // Center the content vertically
    ) {
        // Title text for the signup screen
        Text(
            text = "Sign Up",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp) // Padding below the title
        )

        // Text field for email input
        OutlinedTextField(
            value = email,
            onValueChange = { email = it }, // Update email state on value change
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth() // Fill the width of the parent
        )

        // Text field for password input with visual transformation
        OutlinedTextField(
            value = password,
            onValueChange = { password = it }, // Update password state on value change
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(), // Fill the width of the parent
            visualTransformation = PasswordVisualTransformation() // Hide password input
        )

        // Text field for confirm password input with visual transformation
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it }, // Update confirm password state on value change
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth(), // Fill the width of the parent
            visualTransformation = PasswordVisualTransformation() // Hide confirm password input
        )

        // Display error message if any
        Text(text = errorMessage, color = MaterialTheme.colorScheme.error)

        Spacer(modifier = Modifier.height(16.dp)) // Spacer for spacing between elements

        // Signup button
        Button(
            onClick = {
                // Validate email, password, and confirm password input
                if (Validator.validateSignupInput(email, password, confirmPassword)) {
                    // Attempt signup using the view model
                    viewModel.signup(email, password, onError = { error ->
                        // Update error message on signup failure
                        errorMessage = ErrorHandler.getErrorMessage(error)
                    }, onSuccess = onSignupSuccess)
                } else {
                    // Set error message if validation fails
                    errorMessage = "Invalid input or passwords do not match"
                }
            },
            modifier = Modifier.fillMaxWidth() // Fill the width of the parent
        ) {
            Text(text = "Sign Up")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignupScreen() {
    // Preview function for the signup screen
    SignupScreen(onSignupSuccess = {}, viewModel = MainViewModel())
}