package com.example.travelapp.di

import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.presentation.di.presentationModule

val appModule = listOf(
    presentationModule, domainModule, dataModule
)