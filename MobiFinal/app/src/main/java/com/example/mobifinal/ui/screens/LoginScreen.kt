package com.example.mobifinal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobifinal.ui.viewmodel.LoginViewModel
import com.example.mobifinal.utils.Validator
import com.example.mobifinal.utils.ErrorHandler

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToSignup: () -> Unit,
    viewModel: LoginViewModel
) {
    // State variables to hold email, password, and error message
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Main column layout for the login screen
    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen
            .padding(16.dp), // Padding around the column
        verticalArrangement = Arrangement.Center, // Center the content vertically
    ) {
        // Title text for the login screen
        Text(
            text = "Login",
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

        // Display error message if any
        Text(text = errorMessage, color = MaterialTheme.colorScheme.error)

        Spacer(modifier = Modifier.height(16.dp)) // Spacer for spacing between elements

        // Login button
        Button(
            onClick = {
                // Validate email and password input
                if (Validator.validateLoginInput(email, password)) {
                    // Attempt login using the view model
                    viewModel.login(email, password, onError = { error ->
                        // Update error message on login failure
                        errorMessage = ErrorHandler.getErrorMessage(error)
                    }, onSuccess = onLoginSuccess)
                } else {
                    // Set error message if validation fails
                    errorMessage = "Invalid email or password"
                }
            },
            modifier = Modifier.fillMaxWidth() // Fill the width of the parent
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(8.dp)) // Spacer for spacing between elements

        // Text button to navigate to the signup screen
        TextButton(onClick = onNavigateToSignup) {
            Text(text = "Don't have an account? Sign up")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    // Preview function for the login screen
    LoginScreen(onLoginSuccess = {}, onNavigateToSignup = {}, viewModel = LoginViewModel())
}