package com.alhussain.kmm

import kotlin.random.Random

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    fun generateRandomInt(): Int = Random.nextInt(10,250)
}