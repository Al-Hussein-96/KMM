package com.alhussain.kmm

import com.alhussain.kmm.di.AppModule
import org.koin.core.module.Module

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()


