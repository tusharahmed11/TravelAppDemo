package com.example.travelapp.ui.home

data class DestinationItem(
    val id: String,
    val title: String,
    val location: String,
    val rating: Double,
    val isBookmarked: Boolean = false
)

object DummyHomeData {
    val dummyDestinations = listOf(
        DestinationItem(
            id = "1",
            title = "Niladri Reservoir",
            location = "Tekergat, Sunamgnj",
            rating = 4.7,
            isBookmarked = true
        ),
        DestinationItem(
            id = "2",
            title = "Darma Valley",
            location = "Darma, Uttarakhand",
            rating = 4.8,
            isBookmarked = false
        ),
        DestinationItem(
            id = "3",
            title = "Casa Batllo",
            location = "Barcelona, Spain",
            rating = 4.9,
            isBookmarked = false
        ),
        DestinationItem(
            id = "4",
            title = "Mount Fuji",
            location = "Honshu, Japan",
            rating = 4.9,
            isBookmarked = true
        )
    )
}
