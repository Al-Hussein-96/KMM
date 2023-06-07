package com.alhussain.kmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform