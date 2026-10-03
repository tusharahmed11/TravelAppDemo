package com.example.presentation.di

import com.example.domain.usecase.GetAllListingUseCase
import com.example.domain.usecase.GetAuthTokenUseCase
import com.example.domain.usecase.RegisterUseCase
import com.example.domain.usecase.SignInUseCase
import com.example.presentation.feature.app.AppViewModel
import com.example.presentation.feature.details.TravelListingDetailsViewModel
import com.example.presentation.feature.listings.TravelListingViewModel
import com.example.presentation.feature.register.RegisterViewModel
import com.example.presentation.feature.signin.SignInViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { TravelListingViewModel(get<GetAllListingUseCase>()) }
    viewModel { SignInViewModel(get<SignInUseCase>()) }
    viewModel { RegisterViewModel(get<RegisterUseCase>()) }
    viewModel { (itemID:String)-> TravelListingDetailsViewModel(
        get<GetAllListingUseCase>(),
        get()
    ) }
    viewModel { AppViewModel(get<GetAuthTokenUseCase>()) }
}