package com.example.tawuniya.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tawuniya.data.source.local.entity.UserEntity

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

@Dao
interface TawuniyaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserToFavourites(users: UserEntity)

    @Query("SELECT * FROM favourites_users")
    suspend fun getAllFavouritesUsers(): List<UserEntity>
}