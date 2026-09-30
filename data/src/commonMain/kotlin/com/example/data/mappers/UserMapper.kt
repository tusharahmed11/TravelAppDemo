package com.example.data.mappers

import com.example.data.model.response.signin.UserDto
import com.example.domain.model.UserModel

object UserMapper {
    fun toDomain(dto: UserDto): UserModel {
        return UserModel(
            id = dto.id,
            firstName = dto.firstName,
            lastName = dto.lastName,
            email = dto.email,
        )
    }

    fun toDomain(dtos: List<UserDto>): List<UserModel> {
        return dtos.map { toDomain(it) }
    }
}