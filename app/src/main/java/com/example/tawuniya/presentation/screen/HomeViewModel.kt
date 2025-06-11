package com.example.tawuniya.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tawuniya.data.repo.HomeRepository
import com.example.tawuniya.data.source.remote.dto.UserDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Aziza Helmy on 11/06/2025.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(val repository: HomeRepository) : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState())
    val state = _state.asStateFlow()

    init {
        getAllUsers()
    }

    fun getAllUsers() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            {
                repository.getAllUsers()
            },
            onSuccess = { users ->
                _state.update {
                    it.copy(users = users.toUiState(), isLoading = false)
                }
            },
            onError = { _state.update { it.copy(isError = true, isLoading = false) } })
    }

    fun addUserToFavorites(user: UserUiState) {
        tryToExecute(
            { repository.addUserToFavorites(user.toDto()) },
            onSuccess = {
                _state.update { currentState ->
                    currentState.copy(
                        users = currentState.users.map { currentUser ->
                            if (currentUser == user) {
                                currentUser.copy(isFavorite = !currentUser.isFavorite)
                            } else {
                                currentUser
                            }
                        }
                    )
                }
            },
            onError = { _state.update { it.copy(isError = true) } })
    }

    fun UserUiState.toDto() = UserDto(
        id = 0,
        name = name,
        email = email,
        phone = phone
    )

    fun List<UserDto>.toUiState(): List<UserUiState> {
        return map { userDto ->
            UserUiState(
                name = userDto.name.orEmpty(),
                email = userDto.email.orEmpty(),
                phone = userDto.phone.orEmpty()
            )
        }
    }

    private fun <T> tryToExecute(
        function: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (exception: Exception) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result = function()
                onSuccess(result)
            } catch (exception: Exception) {
                onError(exception)
            }
        }
    }

}