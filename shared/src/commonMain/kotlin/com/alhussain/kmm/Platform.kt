package com.alhussain.kmm

import org.koin.core.module.Module

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

