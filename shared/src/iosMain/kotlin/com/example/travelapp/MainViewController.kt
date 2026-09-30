package com.example.travelapp

import androidx.compose.ui.window.ComposeUIViewController
import com.example.travelapp.di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        startKoin { modules(appModule) }
    }
) { App() }