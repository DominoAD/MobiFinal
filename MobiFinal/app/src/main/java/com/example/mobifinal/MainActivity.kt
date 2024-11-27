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
            AttendanceAppTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        // Navigation Host
                        Box(modifier = Modifier.weight(1f)) {
                            NavHost(navController = navController, startDestination = "login") {
                                composable("login") {
                                    LoginScreen(
                                        onLoginSuccess = { navController.navigate("home") },
                                        onNavigateToSignup = { navController.navigate("signup") },
                                        viewModel = LoginViewModel()
                                    )
                                }
                                composable("home") {
                                    HomeScreen(
                                        onNavigateToAttendance = { navController.navigate("attendance") },
                                        onNavigateToReports = { navController.navigate("reports") },
                                        onNavigateToSettings = { navController.navigate("settings") }
                                    )
                                }
                                composable("signup") {
                                    SignupScreen(
                                        onSignupSuccess = { navController.navigate("home") },
                                        viewModel = MainViewModel()
                                    )
                                }
                                composable("attendance") { AttendanceScreen() }
                                composable("reports") { ReportsScreen() }
                                composable("settings") { SettingsScreen() }
                            }
                        }

                        // Bottom Navigation Bar
                        BottomNavigationBar(navController = navController)
                    }
                }
            }
        }
    }
}