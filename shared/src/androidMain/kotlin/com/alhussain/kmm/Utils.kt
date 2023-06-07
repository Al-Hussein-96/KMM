package com.alhussain.kmm

import kotlin.random.Random

actual fun randomUUID(): String = Random(25).nextInt().toString()