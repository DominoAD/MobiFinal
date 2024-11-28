package com.example.mobifinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobifinal.ui.components.BottomNavigationBar
import com.example.mobifinal.ui.screens.*
import com.example.mobifinal.ui.theme.AttendanceAppTheme
import com.example.mobifinal.ui.viewmodel.LoginViewModel
import com.example.mobifinal.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Apply the custom theme for the app
            AttendanceAppTheme {
                // Create a NavController to manage navigation within the app
                val navController = rememberNavController()
                Surface(
                    // Fill the entire screen with the Surface
                    modifier = Modifier.fillMaxSize(),
                    // Set the background color from the theme
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        // Box to hold the navigation host and take up available space
                        Box(modifier = Modifier.weight(1f)) {
                            // Define the navigation host with the NavController and start destination
                            NavHost(navController = navController, startDestination = "login") {
                                // Define the composable for the login screen
                                composable("login") {
                                    LoginScreen(
                                        // Navigate to home screen on successful login
                                        onLoginSuccess = { navController.navigate("home") },
                                        // Navigate to signup screen if user wants to sign up
                                        onNavigateToSignup = { navController.navigate("signup") },
                                        // Provide the LoginViewModel to the LoginScreen
                                        viewModel = LoginViewModel()
                                    )
                                }
                                // Define the composable for the home screen
                                composable("home") {
                                    HomeScreen(
                                        // Navigate to attendance screen
                                        onNavigateToAttendance = { navController.navigate("attendance") },
                                        // Navigate to reports screen
                                        onNavigateToReports = { navController.navigate("reports") },
                                        // Navigate to settings screen
                                        onNavigateToSettings = { navController.navigate("settings") }
                                    )
                                }
                                // Define the composable for the signup screen
                                composable("signup") {
                                    SignupScreen(
                                        // Navigate to home screen on successful signup
                                        onSignupSuccess = { navController.navigate("home") },
                                        // Provide the MainViewModel to the SignupScreen
                                        viewModel = MainViewModel()
                                    )
                                }
                                // Define the composable for the attendance screen
                                composable("attendance") { AttendanceScreen() }
                                // Define the composable for the reports screen
                                composable("reports") { ReportsScreen() }
                                // Define the composable for the settings screen
                                composable("settings") { SettingsScreen() }
                            }
                        }

                        // Bottom navigation bar to allow switching between main sections
                        BottomNavigationBar(navController = navController)
                    }
                }
            }
        }
    }
}