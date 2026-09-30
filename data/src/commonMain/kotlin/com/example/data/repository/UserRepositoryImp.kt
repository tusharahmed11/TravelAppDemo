package com.example.data.repository

import com.example.data.datasource.RemoteDataSource
import com.example.data.mappers.UserMapper
import com.example.data.model.request.SignInRequest
import com.example.domain.model.UserModel
import com.example.domain.repository.UserRepository

class UserRepositoryImp(val dataSource: RemoteDataSource) : UserRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<UserModel> {
        return try {
            val response = dataSource.signIn(SignInRequest(email, password))
            if (response.isSuccess) {
                val response = response.getOrNull()!!
                val userModel = UserMapper.toDomain(response.user)
                Result.success(userModel)
            } else {
                Result.failure(Exception("Login failed with status code: ${response.exceptionOrNull()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}