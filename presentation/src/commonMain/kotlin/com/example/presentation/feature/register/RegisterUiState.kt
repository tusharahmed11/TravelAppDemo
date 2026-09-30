package com.example.presentation.feature.register

import com.example.domain.model.UserModel

data class RegisterUiState (
    val user: UserModel? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)