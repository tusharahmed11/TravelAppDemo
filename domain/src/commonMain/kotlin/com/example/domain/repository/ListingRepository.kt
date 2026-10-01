package com.example.domain.repository

import com.example.domain.model.TravelListing
import kotlinx.coroutines.flow.Flow

interface ListingRepository {
   suspend fun getAllListings(): Result<List<TravelListing>>
  //  fun getListingById(id: String): Flow<TravelListing?>
}