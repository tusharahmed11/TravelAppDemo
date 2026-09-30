package com.example.domain.usecase

import com.example.domain.model.UserModel
import com.example.domain.repository.UserRepository

class SignInUseCase(private val repository: UserRepository) {
    suspend fun execute(userName: String, password: String): Result<UserModel> {
        return repository.login(userName, password)
    }
}