package com.example.presentation.feature.listings

sealed class AuthNavigation {
    object ToLogin : AuthNavigation()
    object ToSignUp : AuthNavigation()
    object ToListing : AuthNavigation()
}