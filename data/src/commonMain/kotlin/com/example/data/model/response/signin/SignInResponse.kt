package com.example.data.model.response.signin

import kotlinx.serialization.Serializable


@Serializable
data class SignInResponse(
    val token: String,
    val user: UserDto
)