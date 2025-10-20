package com.example.s8104213assignment2.ui.dashboard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
// This import is now lowercase
import com.example.s8104213assignment2.models.Entity
// This import is now lowercase
import com.example.s8104213assignment2.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val apiService: ApiService,
    savedStateHandle: SavedStateHandle // To get the keypass from navigation
) : ViewModel() {

    private val _dashboardState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val dashboardState: StateFlow<DashboardUiState> = _dashboardState

    init {
        // Get the keypass from the navigation argument
        val keypass: String = savedStateHandle.get("keypass") ?: ""
        if (keypass.isNotEmpty()) {
            fetchDashboardData(keypass)
        } else {
            _dashboardState.value = DashboardUiState.Error("No keypass provided")
        }
    }

    private fun fetchDashboardData(keypass: String) {
        viewModelScope.launch {
            _dashboardState.value = DashboardUiState.Loading
            try {
                val response = apiService.getDashboard(keypass)
                if (response.isSuccessful && response.body() != null) {
                    _dashboardState.value = DashboardUiState.Success(response.body()!!.entities)
                } else {
                    _dashboardState.value = DashboardUiState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _dashboardState.value = DashboardUiState.Error("Exception: ${e.message}")
            }
        }
    }
}

sealed interface DashboardUiState {
    object Loading : DashboardUiState
    data class Success(val entities: List<Entity>) : DashboardUiState
    data class Error(val message: String) : DashboardUiState
}