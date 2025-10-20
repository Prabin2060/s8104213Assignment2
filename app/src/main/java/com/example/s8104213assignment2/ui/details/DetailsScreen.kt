package com.example.s8104213assignment2.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
// This import is lowercase
import com.example.s8104213assignment2.models.Entity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, entity: Entity) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(entity.title ?: "Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Go Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Display ALL information using the correct names
            Text(text = "Title", style = MaterialTheme.typography.titleSmall)
            Text(text = entity.title ?: "N/A", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Director", style = MaterialTheme.typography.titleSmall)
            Text(text = entity.director ?: "N/A", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Genre", style = MaterialTheme.typography.titleSmall)
            Text(text = entity.genre ?: "N/A", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Release Year", style = MaterialTheme.typography.titleSmall)
            // .toString() is needed because releaseYear is an Int
            Text(text = entity.releaseYear?.toString() ?: "N/A", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Description", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = entity.description ?: "No description provided.", style = MaterialTheme.typography.bodyLarge)
        }
    }
}