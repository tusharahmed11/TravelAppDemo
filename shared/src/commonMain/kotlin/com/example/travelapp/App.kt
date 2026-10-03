package com.example.travelapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.feature.app.AppViewModel
import com.example.travelapp.navigation.AppNavRoot
import com.example.travelapp.ui.listings.HomeListingScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(viewModel: AppViewModel = koinViewModel()) {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
        ) {
            val uiSource = viewModel.state.collectAsState()
            if (!uiSource.value.isLoading) {
                AppNavRoot(uiSource.value.authToken)
            } else {
                CircularProgressIndicator()
            }


        }
    }
}
