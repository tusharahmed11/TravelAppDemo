package com.example.data.model.response.listing

import kotlinx.serialization.Serializable

@Serializable
data class TravelListingDto(
    val amenities: List<String>,
    val availableFrom: String,
    val availableTo: String?,
    val capacity: Int,
    val category: String,
    val city: String,
    val country: String,
    val createdAt: String,
    val currency: String,
    val description: String,
    val id: String,
    val images: List<String>,
    val isActive: Boolean,
    val location: String,
    val price: Double,
    val rating: Double,
    val reviewCount: Int,
    val title: String,
    val tripDates: List<TripDate>,
    val updatedAt: String,
    val vendorId: String
)