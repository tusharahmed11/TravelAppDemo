package com.example.travelapp.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.presentation.feature.details.TravelListingDetailsViewModel
import io.ktor.http.parametersOf
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TravelItemDetailsScreen(
    navStack: NavBackStack<NavKey>,
    itemId: String,
    viewModel: TravelListingDetailsViewModel = koinViewModel{ parametersOf(itemId) }
){
    Column {
        Text("Travel Item Details Screen for: $itemId ")
    }
}