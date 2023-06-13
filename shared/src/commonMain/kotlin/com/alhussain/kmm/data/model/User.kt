package com.alhussain.kmm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(val data: Data){

    @Serializable

    data class Data(val status: Boolean?, val payload: Payload)

    @Serializable
    data class Payload(
        val accessToken: String
    )
}


@Serializable
data class UserLogin(@SerialName("identity") val username: String, val password: String)
