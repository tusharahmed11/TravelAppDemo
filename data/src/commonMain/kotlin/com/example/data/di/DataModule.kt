package com.example.data.di

import com.example.data.datasource.DummyDataSource
import com.example.data.repository.ListingRepositoryImpl
import com.example.domain.repository.ListingRepository
import org.koin.dsl.module

val dataModule = module {
    single { DummyDataSource() }

    single<ListingRepository> {
        ListingRepositoryImpl(
            get()
        )
    }
}