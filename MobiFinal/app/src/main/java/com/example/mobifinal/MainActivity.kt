package com.example.mobifinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobifinal.data.MyDatabaseHelper
import com.example.mobifinal.ui.screens.AttendanceScreen
import com.example.mobifinal.ui.screens.HomeScreen
import com.example.mobifinal.ui.screens.LoginScreen
import com.example.mobifinal.ui.screens.ReportsScreen
import com.example.mobifinal.ui.screens.SettingsScreen
import com.example.mobifinal.ui.screens.SignupScreen
import com.example.mobifinal.ui.theme.AttendanceAppTheme
import com.example.mobifinal.ui.viewmodel.LoginViewModel
import com.example.mobifinal.ui.viewmodel.SignUpViewModel

class MainActivity : ComponentActivity() {
    private lateinit var databaseHelper: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = MyDatabaseHelper(this)

        val signUpViewModel: SignUpViewModel by viewModels {
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SignUpViewModel(databaseHelper) as T
                }
            }
        }

        val loginViewModel: LoginViewModel by viewModels {
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return LoginViewModel(databaseHelper) as T
                }
            }
        }

        setContent {
            AttendanceAppTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = "login") {
                        composable("signup") {
                            SignupScreen(
                                onSignupSuccess = { navController.navigate("login") },
                                onNavigateToLogin = { navController.navigate("login") },
                                viewModel = signUpViewModel
                            )
                        }
                        composable("login") {
                            LoginScreen(
                                onLoginSuccess = { navController.navigate("home") },
                                onNavigateToSignup = { navController.navigate("signup") },
                                viewModel = loginViewModel
                            )
                        }
                        composable("home") {
                            HomeScreen(
                                onNavigateToAttendance = { navController.navigate("attendance") },
                                onNavigateToReports = { navController.navigate("reports") },
                                onNavigateToSettings = { navController.navigate("settings") }
                            )
                        }
                        composable("attendance") {
                            AttendanceScreen(navController, databaseHelper)
                        }
                        composable("reports") {
                            ReportsScreen(databaseHelper, navController)
                        }
                        composable("settings") {
                            SettingsScreen(
                                onNavigateToHome = { navController.navigate("home") },
                                onNavigateToAttendance = { navController.navigate("attendance") },
                                onNavigateToReports = { navController.navigate("reports") },
                                onNavigateToSettings = { navController.navigate("settings") }
                            )
                        }
                    }
                }
            }
        }
    }
}