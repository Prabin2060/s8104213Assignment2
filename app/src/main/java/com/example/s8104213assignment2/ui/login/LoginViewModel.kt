package com.example.s8104213assignment2.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
// This import is now lowercase
import com.example.s8104213assignment2.models.LoginRequest
// This import is now lowercase
import com.example.s8104213assignment2.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    // Holds the state for the UI
    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginState: StateFlow<LoginUiState> = _loginState

    fun login(username: String, studentId: String, location: String) {
        viewModelScope.launch {
            _loginState.value = LoginUiState.Loading
            try {
                // Use student first name and ID for credentials
                val request = LoginRequest(username = username, password = studentId)

                // Make the POST request
                val response = apiService.login(location, request)

                if (response.isSuccessful && response.body() != null) {
                    // On success, update state with the keypass
                    _loginState.value = LoginUiState.Success(response.body()!!.keypass)
                } else {
                    // Handle errors
                    _loginState.value = LoginUiState.Error("Login Failed: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _loginState.value = LoginUiState.Error("Network Error: ${e.message}")
            }
        }
    }
}

// Sealed interface to represent UI states
sealed interface LoginUiState {
    object Idle : LoginUiState
    object Loading : LoginUiState
    data class Success(val keypass: String) : LoginUiState
    data class Error(val message: String) : LoginUiState
}