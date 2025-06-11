package com.example.tawuniya.presentation.screen.composable

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    confirmButtonText: String = "Ok",
    dismissButtonText: String = "Cancel",
    onDismissClicked: () -> Unit,
    onConfirmClicked: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissClicked,
        title = { Text(text = title) },
        text = { Text(text = message) },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmClicked()
                    onDismissClicked()
                }
            ) {
                Text(text = confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissClicked) {
                Text(text = dismissButtonText)
            }
        }
    )
}
