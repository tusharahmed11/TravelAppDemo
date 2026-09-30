package com.example.travelapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform