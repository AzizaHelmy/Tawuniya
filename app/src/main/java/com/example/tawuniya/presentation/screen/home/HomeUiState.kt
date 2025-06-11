package com.example.tawuniya.presentation.screen.home

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val users: List<UserUiState> = emptyList(),
    val showSnackbar: Boolean = false,
    val showDeleteDialog: Boolean = false,
    val userToDelete: UserUiState? = null
)

data class UserUiState(
    val name: String,
    val email: String,
    val phone: String,
    val isFavorite: Boolean = false
)
