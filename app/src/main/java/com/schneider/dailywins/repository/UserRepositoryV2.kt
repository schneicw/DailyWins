package com.schneider.dailywins.repository

import com.schneider.dailywins.data.User
import com.schneider.dailywins.network.UserServiceV2
import com.schneider.dailywins.responses.UserResponse

class UserRepositoryV2 (
    private val api: UserServiceV2
) : BaseRepository() {

    suspend fun login (
        email: String,
        password: String
    ) = safeApiCall {
        val user: User = User(email, password, true)
        api.createUser(user)
    }

}