package com.example.data.repository

import com.example.data.datasource.CacheDataSource
import com.example.domain.repository.CacheRepository

class CacheRepositoryImpl(private val cacheDataSource: CacheDataSource) : CacheRepository {
    override suspend fun getAuthToken(): String? {
        return cacheDataSource.getAuthToken()
    }
}