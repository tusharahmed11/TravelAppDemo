package com.example.presentation.feature.details

import com.example.domain.model.TravelListing

data class TravelListingDetailsUiState(
    val listing: TravelListing? = null,
    val isLoading:Boolean = false,
    val errorMessage:String? = null
) {

}