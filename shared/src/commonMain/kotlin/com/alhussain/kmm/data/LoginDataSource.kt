package com.alhussain.kmm.data

import com.alhussain.kmm.Greeting
import com.alhussain.kmm.data.model.User
import com.alhussain.kmm.randomUUID
import kotlinx.coroutines.delay

class LoginDataSource {

    suspend fun login(username: String, password: String): Result<User> {
        return try {
            // TODO: handle loggedInUser authentication
            delay(1500)

            val flag = Greeting().generateRandomInt()

            if(flag%2 == 0) throw Exception("Exception")

            val fakeUser = User(randomUUID(), "Jane Doe")
            Result.Success(fakeUser)
        } catch (e: Throwable) {
            Result.Error(RuntimeException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}