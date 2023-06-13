package com.alhussain.kmm

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.alhussain.kmm.data.datasource.dataStoreFileName
import com.alhussain.kmm.data.datasource.getDataStore
import org.koin.dsl.module

fun getDataStore(context: Context): DataStore<Preferences> = getDataStore(
    producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
)
