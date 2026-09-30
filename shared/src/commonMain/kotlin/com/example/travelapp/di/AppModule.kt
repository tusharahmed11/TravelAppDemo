package com.example.travelapp.di

import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.presentation.di.presentationModule
import org.koin.core.module.Module

val appModule = listOf(
    platformModule(),presentationModule, domainModule, dataModule
)

expect fun platformModule() : Module