package com.alhussain.kmm.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.alhussain.kmm.data.model.User
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SessionManager(private val dataStore: DataStore<Preferences>) {


    private val scope = CoroutineScope(Dispatchers.Default)

    private val tokenKey = stringPreferencesKey("accessToken")


    fun getSessionManagerName() = "Session Manager"


    val token: Flow<String> = dataStore.data.map {
        it[tokenKey] ?: ""
    }



    fun saveToken(
        token: String,
    ) {
        scope.launch {
            dataStore.edit {
                it[tokenKey] = token
            }
        }
    }

}