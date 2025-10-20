package com.example.s8104213assignment2.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
// This import is lowercase
import com.example.s8104213assignment2.models.Entity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState = viewModel.dashboardState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Dashboard") })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (uiState) {
                is DashboardUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is DashboardUiState.Success -> {
                    // LazyColumn is the Compose version of RecyclerView
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(uiState.entities) { entity ->
                            DashboardItem(
                                entity = entity,
                                onClick = {
                                    // Set the entity in NavController's back stack entry
                                    // This is a safer way to pass complex objects
                                    navController.currentBackStackEntry?.savedStateHandle?.set("entity", entity)
                                    navController.navigate("details")
                                }
                            )
                        }
                    }
                }
                is DashboardUiState.Error -> {
                    Text(
                        text = uiState.message,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun DashboardItem(entity: Entity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Display summary, EXCLUDING description

            // THIS IS THE FIX: Using ?: to handle null values
            Text(text = "Property 1: ${entity.property1 ?: "N/A"}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Property 2: ${entity.property2 ?: "N/A"}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}