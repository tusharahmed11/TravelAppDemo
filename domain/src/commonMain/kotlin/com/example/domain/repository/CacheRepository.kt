package com.example.domain.repository

interface CacheRepository {
    suspend fun getAuthToken(): String?
}