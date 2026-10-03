package com.example.domain.di

import com.example.domain.repository.CacheRepository
import com.example.domain.repository.ListingRepository
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.GetAllListingUseCase
import com.example.domain.usecase.GetAuthTokenUseCase
import com.example.domain.usecase.RegisterUseCase
import com.example.domain.usecase.SignInUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetAllListingUseCase(get<ListingRepository>())
    }

    factory {
        SignInUseCase(get<UserRepository>())
    }

    factory {
        RegisterUseCase(get<UserRepository>())
    }
    factory {
        GetAuthTokenUseCase(get<CacheRepository>())
    }
}