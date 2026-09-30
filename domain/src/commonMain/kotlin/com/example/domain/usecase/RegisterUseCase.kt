package com.example.domain.usecase

import com.example.domain.model.RegisterModel
import com.example.domain.model.UserModel
import com.example.domain.repository.UserRepository

class RegisterUseCase(private val userRepository: UserRepository) {

    suspend fun execute(request: RegisterModel): Result<UserModel> {
        return userRepository.register(request)
    }
}