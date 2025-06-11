package com.example.tawuniya.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tawuniya.data.source.remote.dto.UserDto

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

@Entity(tableName = "favourites_users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val isFavorite: Boolean
)

fun UserDto.toEntity() = UserEntity(
    id = id ?: 0,
    name = name.orEmpty(),
    email = email.orEmpty(),
    phone = phone.orEmpty(),
    isFavorite = isFavorite
)

fun List<UserEntity>.toDto() = map { userEntity ->
    UserDto(
        id = userEntity.id,
        name = userEntity.name,
        email = userEntity.email,
        phone = userEntity.phone,
        isFavorite = userEntity.isFavorite
    )
}
