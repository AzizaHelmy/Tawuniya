package com.example.tawuniya.data.source.remote

import com.example.tawuniya.data.source.remote.model.UserDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

fun interface TawuniyaApiService {

    @GET("users")
    suspend fun getAllUsers(): Response<List<UserDto>>
}