package com.example.domain.model

data class TravelListing(
    val id: String,
    val title: String,
    val location: String,
    val rating: Double,
    val imageUrl: List<String>,
    val isFavorite: Boolean = false
)