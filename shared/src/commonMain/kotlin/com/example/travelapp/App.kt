package com.example.travelapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.datasource.DummyDataSource
import com.example.data.repository.ListingRepositoryImpl
import com.example.domain.usecase.GetAllListingUseCase
import com.example.presentation.listings.TravelListingViewModel
import com.example.travelapp.listings.TravelListingScreen
import org.jetbrains.compose.resources.painterResource

import travelapp.shared.generated.resources.Res
import travelapp.shared.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        TravelListingScreen()
    }
}