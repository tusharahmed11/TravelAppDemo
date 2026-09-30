package com.example.presentation.di

import com.example.domain.usecase.GetAllListingUseCase
import com.example.domain.usecase.SignInUseCase
import com.example.presentation.feature.listings.TravelListingViewModel
import com.example.presentation.feature.signin.SignInViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { TravelListingViewModel(get<GetAllListingUseCase>()) }
    viewModel { SignInViewModel(get<SignInUseCase>()) }
}