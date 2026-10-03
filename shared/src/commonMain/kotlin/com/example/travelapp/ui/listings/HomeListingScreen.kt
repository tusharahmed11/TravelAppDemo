package com.example.travelapp.ui.listings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.presentation.feature.listings.TravelListingViewModel
import com.example.travelapp.navigation.NavRoutes
import com.example.travelapp.ui.home.DestinationItem
import com.example.travelapp.ui.home.DummyHomeData
import com.example.travelapp.widget.AppCircleImageButton
import com.example.travelapp.widget.AppDestinationCard
import com.example.travelapp.widget.AppSpacer
import com.example.travelapp.widget.AppUserProfileBadge
import com.example.travelapp.widget.HighlightedHeading
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeListingScreen(
    backStack: NavBackStack<NavKey>,
    userName: String = "Leonardo",
    onNotificationClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onViewAllClick: () -> Unit = {},
    viewModel: TravelListingViewModel = koinViewModel()
) {

    val uiState = viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        AppSpacer(12.dp)

        // Top App Bar: User Profile Badge on the left, Notification Icon on the right
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppUserProfileBadge(
                name = userName,
                onClick = onProfileClick
            )

            AppCircleImageButton(
                onClick = onNotificationClick,
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notifications"
            )
        }

        AppSpacer(24.dp)

        // Highlighted Heading: "Explore the Beautiful world!"
        HighlightedHeading(
            normalText = "Explore the",
            boldText = "Beautiful",
            highlightedText = "world!",
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        AppSpacer(24.dp)

        // Section Title: "Best Destination" with "View all"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Best Destination",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B1E28)
            )

            Text(
                text = "View all",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF0D6EFD),
                modifier = Modifier.clickable(onClick = onViewAllClick)
            )
        }

        AppSpacer(16.dp)

        // Horizontal Carousel of Destination Cards

        if (uiState.value.isLoading){
            CircularProgressIndicator()
        }

        uiState.value.errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }

        uiState.value.listings.takeIf { it.isNotEmpty() }?.let { listingList ->
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(listingList, key = { it.id }) { listing ->
                    AppDestinationCard(
                        travelListing = listing,
                        onCardClick = { clickedListing->
                            backStack.add(NavRoutes.ListingDetails(id = clickedListing.id))
                        }
                    )
                }
            }
        }



        AppSpacer(28.dp)
    }
}
