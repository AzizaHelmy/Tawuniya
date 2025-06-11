package com.example.tawuniya.data.repo

import com.example.tawuniya.data.source.remote.model.UserDto

/**
 * Created by Aziza Helmy on 11/06/2025.
 */
interface HomeRepository {

    suspend fun getAllUsers(): List<UserDto>

    suspend fun addUserToFavorites(user: UserDto)

}