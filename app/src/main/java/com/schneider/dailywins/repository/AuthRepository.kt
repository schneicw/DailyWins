package com.schneider.dailywins.repository

import com.schneider.dailywins.network.AuthApi


class AuthRepository(
    private val api: AuthApi
) : BaseRepository() {

    suspend fun login (
        email: String,
        password: String
    ) = safeApiCall {
        api.login( email, password)
    }
}