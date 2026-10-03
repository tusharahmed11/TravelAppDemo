package com.example.domain.usecase

import com.example.domain.repository.CacheRepository

class GetAuthTokenUseCase(private val repository: CacheRepository) {
    suspend fun execute(): String? {
        return repository.getAuthToken()
    }
}