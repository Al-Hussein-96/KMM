package com.alhussain.kmm

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.alhussain.kmm.data.datasource.dataStoreFileName
import com.alhussain.kmm.data.datasource.getDataStore
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask


fun createDataStore(): DataStore<Preferences> = getDataStore(
    producePath = {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        requireNotNull(documentDirectory).path + "/$dataStoreFileName"
    }
)

