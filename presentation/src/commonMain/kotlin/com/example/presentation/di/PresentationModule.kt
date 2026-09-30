package com.example.presentation.di

import com.example.domain.usecase.GetAllListingUseCase
import com.example.presentation.listings.TravelListingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val presentationModule = module {
    viewModel { TravelListingViewModel(get<GetAllListingUseCase>()) }
}