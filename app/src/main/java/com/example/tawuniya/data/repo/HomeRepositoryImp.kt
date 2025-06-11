package com.example.tawuniya.data.repo

import com.example.tawuniya.data.source.remote.TawuniyaApiService
import com.example.tawuniya.data.source.remote.model.UserDto
import com.example.tawuniya.data.util.InvalidResponse
import com.example.tawuniya.data.util.NetworkError
import com.example.tawuniya.data.util.NotFound
import com.example.tawuniya.data.util.TawuniyaException
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

class HomeRepositoryImp @Inject constructor(
    val remoteDataSource: TawuniyaApiService
) : HomeRepository {

    override suspend fun getAllUsers(): List<UserDto> {
        return wrap { remoteDataSource.getAllUsers() }
    }

    override suspend fun addUserToFavorites(user: UserDto) {

    }


    suspend fun <T : Any> wrap(function: suspend () -> Response<T>): T {
        return try {
            val response = function()
            if (response.isSuccessful) {
                response.body() ?: throw InvalidResponse("Response body is null")
            } else {
                when (response.code()) {
                    401 -> throw NetworkError("Unauthorized access")
                    403 -> throw NetworkError("Access forbidden")
                    404 -> throw NotFound("users not found")
                    500 -> throw NetworkError("Server error")
                    else -> throw NetworkError(
                        "Error: ${
                            response.errorBody()?.string()
                        }"
                    )
                }
            }
        } catch (e: Exception) {
            when (e) {
                is TawuniyaException -> throw e
                is java.net.ConnectException -> throw NetworkError("Failed to connect to server")
                is java.net.SocketTimeoutException -> throw NetworkError("Connection timeout")
                is retrofit2.HttpException -> throw NetworkError("HTTP error: ${e.code()}")
                else -> throw NotFound("Unexpected error: ${e.message}")
            }
        }
    }

}