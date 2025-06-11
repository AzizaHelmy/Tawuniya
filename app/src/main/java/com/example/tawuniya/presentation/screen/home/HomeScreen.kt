package com.example.tawuniya.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tawuniya.R
import com.example.tawuniya.presentation.screen.composable.ConfirmationDialog

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeContent(
        state = state,
        onRetry = viewModel::getAllUsers,
        onFavoriteClick = { user ->
            if (user.isFavorite) {
                viewModel.showDeleteDialog(user)
            } else {
                viewModel.addUserToFavorites(user)
            }
        },
        onDismissSnackbar = { viewModel.dismissSnackbar() },
        onConfirmDelete = {
            state.userToDelete?.let { user ->
                viewModel.addUserToFavorites(user)
            }
            viewModel.dismissDeleteDialog()
        },
        onDismissDeleteDialog = { viewModel.dismissDeleteDialog() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    state: HomeUiState,
    onRetry: () -> Unit,
    onFavoriteClick: (UserUiState) -> Unit,
    onDismissSnackbar: () -> Unit,
    onConfirmDelete: () -> Unit,
    onDismissDeleteDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val message = if (state.userToDelete?.isFavorite == true) {
        stringResource(R.string.user_removed_from_favorites)
    } else {
        stringResource(R.string.user_added_to_favorites)
    }

    LaunchedEffect(state.showSnackbar) {
        if (state.showSnackbar) {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
            onDismissSnackbar()
        }
    }

    if (state.showDeleteDialog) {
        ConfirmationDialog(
            title = stringResource(R.string.remove_from_favorites),
            message = stringResource(
                R.string.are_you_sure_you_want_to_remove_from_favorites,
                state.userToDelete?.name ?: ""
            ),
            confirmButtonText = stringResource(R.string.remove),
            dismissButtonText = stringResource(R.string.cancel),
            onDismissClicked = onDismissDeleteDialog,
            onConfirmClicked = onConfirmDelete
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                title = {
                    Text("Tawuniya Users")
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                state.isError -> {
                    FailedStateUi(state.errorMessage, onRetry)
                }

                state.users.isEmpty() -> {
                    Text(
                        text = "No users found",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    SuccessStateUi(state, onFavoriteClick)
                }
            }
        }
    }
}


@Composable
private fun FailedStateUi(errorMessage: String?, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorMessage ?: stringResource(R.string.error_message),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onRetry() }) {
            Text("Retry")
        }
    }
}

@Composable
private fun SuccessStateUi(
    state: HomeUiState,
    onFavoriteClick: (UserUiState) -> Unit
) {
    LazyColumn(
        modifier = Modifier.background(Color(0xFFF2F2F2)),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 4.dp
        )
    ) {
        itemsIndexed(items = state.users) { _, user ->
            UserItem(
                user = user,
                onFavoriteClick = { onFavoriteClick(user) }
            )
        }
    }
}

@Composable
private fun UserItem(
    user: UserUiState,
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = Icons.Outlined.Favorite,
                        contentDescription = "Favorite",
                        tint = if (user.isFavorite) Color.Red else Color.Gray
                    )
                }
            }
            Text(
                text = user.email,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = user.phone,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
