package com.example.tawuniya.data.util

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

sealed class TawuniyaException : Exception()
data class NetworkError(override val message: String) : TawuniyaException()
data class InvalidResponse(override val message: String) : TawuniyaException()
data class NotFound(override val message: String) : TawuniyaException()
