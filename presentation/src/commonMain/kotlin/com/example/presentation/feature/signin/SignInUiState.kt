package com.example.presentation.feature.signin

import com.example.domain.model.UserModel

data class SignInUiState(
    val user: UserModel? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)