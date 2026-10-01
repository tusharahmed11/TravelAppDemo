package com.example.travelapp.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoutes : NavKey{
    @Serializable
    data object Login : NavRoutes, NavKey
    @Serializable
    data object SignUp : NavRoutes, NavKey
    @Serializable
    data object Listing : NavRoutes, NavKey
}