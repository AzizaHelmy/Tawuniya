package com.example.tawuniya.data.repo

import com.example.tawuniya.data.source.remote.dto.UserDto

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

interface HomeRepository {

    suspend fun getAllUsers(): List<UserDto>

    suspend fun addUserToFavorites(user: UserDto)

    suspend fun deleteUserFromFavorites(user: UserDto)

    suspend fun getAllFavouritesUsers(): List<UserDto>


}