package com.alhussain.kmm

import android.content.Context
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun KMM.Companion.create(ctx: Context, withLog: Boolean) = KMM(

).also {
    if (withLog) Napier.base(DebugAntilog())


}