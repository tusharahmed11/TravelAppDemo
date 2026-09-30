package com.example.data.mappers

import com.example.data.model.request.RegisterRequest
import com.example.domain.model.RegisterModel

object RegisterRequestMapper {
    fun toDomain(dto: RegisterRequest): RegisterModel {
        return RegisterModel(
            email = dto.email,
            firstName = dto.firstName,
            lastName = dto.lastName,
            password = dto.password,
            phone = dto.phone,
            role = dto.role
        )
    }

    fun toDomain(dtos: List<RegisterRequest>): List<RegisterModel> {
        return dtos.map { toDomain(it) }
    }

    fun toDto(domain: RegisterModel): RegisterRequest {
        return RegisterRequest(
            email = domain.email,
            firstName = domain.firstName,
            lastName = domain.lastName,
            password = domain.password,
            phone = domain.phone,
            role = domain.role
        )
    }
}