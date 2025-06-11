package com.example.tawuniya.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tawuniya.data.source.remote.dto.UserDto

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

@Entity(tableName = "favourites_users")
data class UserEntity(
    val name: String,
    @PrimaryKey
    val email: String,
    val phone: String,
    val isFavorite: Boolean
)


//Todo: Move the Mappers to a separate class
fun UserDto.toEntity() = UserEntity(
    name = name.orEmpty(),
    email = email.orEmpty(),
    phone = phone.orEmpty(),
    isFavorite = isFavorite
)

fun List<UserEntity>.toDto() = map { userEntity ->
    UserDto(
        name = userEntity.name,
        email = userEntity.email,
        phone = userEntity.phone,
        isFavorite = userEntity.isFavorite
    )
}
