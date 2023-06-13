package com.alhussain.kmm.data

import com.alhussain.kmm.data.model.User
import io.github.aakira.napier.Napier

class LoginRepository(
    private val dataSource: LoginDataSource,
    private val sessionManager: SessionManager
) {

    // in-memory cache of the loggedInUser object
    var user: User? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null



    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    suspend fun login(username: String, password: String): Result<User> {
        // handle login

        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            sessionManager.saveToken(result.data.data.payload.accessToken)
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: User) {
        this.user = loggedInUser
    }
}