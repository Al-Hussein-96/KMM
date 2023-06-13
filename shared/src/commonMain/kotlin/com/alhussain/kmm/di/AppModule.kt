package com.alhussain.kmm.di

import com.alhussain.kmm.data.LoginDataSource
import com.alhussain.kmm.data.LoginRepository
import com.alhussain.kmm.data.SessionManager
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val AppModule = module {

    singleOf(::LoginDataSource)

    singleOf(::LoginRepository)



    single {
        CoroutineScope(Dispatchers.Main)
    }




    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                })
            }
            install(Logging) {
                logger = object : io.ktor.client.plugins.logging.Logger {
                    override fun log(message: String) {
                        Napier.v("HTTP Client", null, message)
                    }
                }
                level = LogLevel.BODY

            }.also {
                Napier.base(DebugAntilog())
            }

            val sessionManager = get<SessionManager>()

            val scope = get<CoroutineScope>()

            scope.launch {
                Napier.i("load token")

                val token = sessionManager.token.first()

                install(Auth) {

                    bearer {
                        loadTokens {
                            Napier.i("token $token")
                            BearerTokens(token, token)
                        }
                    }
                }


            }


        }
    }
}