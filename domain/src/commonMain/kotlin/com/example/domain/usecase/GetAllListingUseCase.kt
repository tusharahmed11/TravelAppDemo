package com.example.domain.usecase

import com.example.domain.model.TravelListing
import com.example.domain.repository.ListingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllListingUseCase(private val repository: ListingRepository) {
    suspend fun execute(): List<TravelListing> {
        val data = repository.getAllListings()
        return if (data.isSuccess){
            data.getOrNull()!!
        }else{
            emptyList<TravelListing>()
        }
    }

}