package com.example.data.mappers

import com.example.data.model.response.listing.TravelListingDto
import com.example.domain.model.TravelListing

object TravelListingMapper {
    fun toDomain(dto: TravelListingDto): TravelListing {
        return TravelListing(
            id = dto.id,
            title = dto.title,
            location = dto.location,
            imageUrl = dto.images,
            rating = dto.rating,
        )
    }

    fun toDomain(dtos: List<TravelListingDto>): List<TravelListing> {
        return dtos.map { toDomain(it) }
    }
}