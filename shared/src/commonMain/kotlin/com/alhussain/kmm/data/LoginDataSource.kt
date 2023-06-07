package com.alhussain.kmm.data

import com.alhussain.kmm.data.model.User
import com.alhussain.kmm.randomUUID

class LoginDataSource {

    fun login(username: String, password: String): Result<User> {
        return try {
            // TODO: handle loggedInUser authentication
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