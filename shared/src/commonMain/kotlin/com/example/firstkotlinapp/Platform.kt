package com.example.firstkotlinapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform