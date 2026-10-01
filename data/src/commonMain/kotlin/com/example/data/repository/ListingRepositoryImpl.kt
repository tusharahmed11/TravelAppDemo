package com.example.data.repository

import com.example.data.datasource.DummyDataSource
import com.example.data.datasource.RemoteDataSource
import com.example.data.mappers.TravelListingMapper
import com.example.domain.model.TravelListing
import com.example.domain.repository.ListingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlin.contracts.Returns

class ListingRepositoryImpl(val dataSource: RemoteDataSource) : ListingRepository {

    override suspend fun getAllListings(): Result<List<TravelListing>> {
        val dtos = dataSource.getAllListings()
        if (dtos.isSuccess){
            val listing = dtos.getOrNull()!!.listings
            val models = TravelListingMapper.toDomain(listing)
            return Result.success(models)
        }else{
            throw dtos.exceptionOrNull()!!
        }
    }

/*    override fun getListingById(id: String): Flow<TravelListing?> {
        return listings.map { list ->
            list.find { it.id == id }
        }
    }*/
}