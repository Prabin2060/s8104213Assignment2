package com.example.s8104213assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
// All imports are lowercase
import com.example.s8104213assignment2.models.Entity
import com.example.s8104213assignment2.ui.dashboard.DashboardScreen
import com.example.s8104213assignment2.ui.details.DetailsScreen
import com.example.s8104213assignment2.ui.login.LoginScreen
import com.example.s8104213assignment2.ui.theme.S8104213assignment2Theme // lowercase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // This Theme name call is now lowercase
            S8104213assignment2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "login") {

                        // Login Screen
                        composable("login") {
                            LoginScreen(navController = navController)
                        }

                        // Dashboard Screen
                        composable(
                            route = "dashboard/{keypass}",
                            arguments = listOf(navArgument("keypass") { type = NavType.StringType })
                        ) {
                            DashboardScreen(navController = navController)
                        }

                        // Details Screen
                        composable(route = "details") {
                            // Get the entity from the previous screen
                            val entity = navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.get<Entity>("entity")

                            if (entity != null) {
                                DetailsScreen(navController = navController, entity = entity)
                            } else {
                                // Handle error
                                Text("Error: Could not load details.")
                            }
                        }
                    }
                }
            }
        }
    }
}