package com.example.domain.model

data class RegisterModel(
    val email: String,
    val firstName: String,
    val lastName: String  = " ",
    val password: String,
    val phone: String = " ",
    val role: String = "Customer"
)