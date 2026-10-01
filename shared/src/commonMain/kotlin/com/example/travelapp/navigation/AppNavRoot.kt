package com.example.travelapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.travelapp.ui.listings.HomeListingScreen
import com.example.travelapp.ui.signin.LoginScreen
import com.example.travelapp.ui.signup.SignUpScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic


@Composable
fun AppNavRoot(){

    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class){
                    subclass(NavRoutes.Login::class, NavRoutes.Login.serializer())
                    subclass(NavRoutes.SignUp::class, NavRoutes.SignUp.serializer())
                    subclass(NavRoutes.Listing::class, NavRoutes.Listing.serializer())
                }
            }
        },
        NavRoutes.Login
    )

    NavDisplay(
        backStack = backStack,
        entryProvider = {key->

            when(key){
                is NavRoutes.Login -> NavEntry(key){
                    LoginScreen(backStack = backStack)
                }
                is NavRoutes.SignUp -> NavEntry(key){
                    SignUpScreen(backStack = backStack)
                }
                is NavRoutes.Listing -> NavEntry(key){
                    HomeListingScreen(backStack = backStack)
                }

                else -> error("Unknown NavRoute: $key")
            }
        }
    )

}