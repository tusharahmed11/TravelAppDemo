package com.example.travelapp.di

import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<String> { "http://127.0.0.1:8080/" }
}