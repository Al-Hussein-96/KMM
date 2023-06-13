package com.alhussain.kmm.data

import com.alhussain.kmm.Greeting
import com.alhussain.kmm.data.model.User
import com.alhussain.kmm.data.model.UserLogin
import com.alhussain.kmm.randomUUID
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginDataSource : KoinComponent {

    private val httpClient: HttpClient by inject()


    suspend fun login(username: String, password: String): Result<User> {
        Napier.i("Ktor response: login")

        val response =
            httpClient.post("https://security.uat.shopiniworld.com/api/parcel-hub-users/mobile-login") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(UserLogin(username, password))
            }

        Napier.i("Ktor response: $response")

        print("response: $response")

        return if (response.status.value in 200..299) {
            Result.Success(response.body() as User)
        } else {
            Result.Error(RuntimeException("Error logging in"))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}