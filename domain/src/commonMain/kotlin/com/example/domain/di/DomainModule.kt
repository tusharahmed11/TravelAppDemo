package com.example.domain.di

import com.example.domain.repository.ListingRepository
import com.example.domain.usecase.GetAllListingUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetAllListingUseCase(get<ListingRepository>())
    }
}