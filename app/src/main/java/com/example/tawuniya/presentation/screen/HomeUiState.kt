package com.example.tawuniya.presentation.screen

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val users: List<UserUiState> = emptyList()
)

data class UserUiState(
    val name: String,
    val email: String,
    val phone: String,
    val isFavorite: Boolean = false
)
