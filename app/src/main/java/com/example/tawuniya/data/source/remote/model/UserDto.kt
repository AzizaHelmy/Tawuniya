package com.example.tawuniya.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserDto(
    @SerialName("address")
    val address: Address? = null,
    @SerialName("company")
    val company: Company? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("phone")
    val phone: String? = null,
    @SerialName("username")
    val username: String? = null,
    @SerialName("website")
    val website: String? = null
) {
    @Serializable
    data class Address(
        @SerialName("city")
        val city: String? = null,
        @SerialName("geo")
        val geo: Location? = null,
        @SerialName("street")
        val street: String? = null,
        @SerialName("suite")
        val suite: String? = null,
        @SerialName("zipcode")
        val zipcode: String? = null
    )

    @Serializable
    data class Company(
        @SerialName("bs")
        val bs: String? = null,
        @SerialName("catchPhrase")
        val catchPhrase: String? = null,
        @SerialName("name")
        val name: String? = null
    )

    @Serializable
    data class Location(
        @SerialName("lat")
        val lat: String? = null,
        @SerialName("lng")
        val lng: String? = null
    )
}

