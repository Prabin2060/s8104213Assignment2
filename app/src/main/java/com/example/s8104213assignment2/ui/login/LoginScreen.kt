package com.example.s8104213assignment2.ui.login



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    // Your details are pre-filled
    var username by remember { mutableStateOf("prabin") }
    var studentId by remember { mutableStateOf("8104213") }
    // Your confirmed location
    val location = "sydney"

    val loginState = viewModel.loginState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = studentId,
            onValueChange = { studentId = it },
            label = { Text("Student ID (Password)") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.login(username, studentId, location)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = loginState !is LoginUiState.Loading
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Handle the login state
        when (loginState) {
            is LoginUiState.Loading -> CircularProgressIndicator()
            is LoginUiState.Success -> {
                // On success, navigate to Dashboard
                LaunchedEffect(key1 = loginState) {
                    navController.navigate("dashboard/${loginState.keypass}") {
                        // Clear the login screen from back stack
                        popUpTo("login") { inclusive = true }
                    }
                }
            }
            is LoginUiState.Error -> {
                // Display error message
                Text(text = loginState.message, color = Color.Red)
            }
            is LoginUiState.Idle -> { /* Do nothing */ }
        }
    }
}