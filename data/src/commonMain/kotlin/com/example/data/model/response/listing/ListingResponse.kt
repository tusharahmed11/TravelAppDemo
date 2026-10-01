package com.example.data.model.response.listing

import kotlinx.serialization.Serializable

@Serializable
data class ListingResponse(
    val listings: List<TravelListingDto>,
    val page: Int,
    val pageSize: Int,
    val total: Int
)