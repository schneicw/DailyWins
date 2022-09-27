package com.schneider.dailywins.network

import com.schneider.dailywins.data.User
import com.schneider.dailywins.responses.UserResponse
import retrofit2.http.*

interface UserServiceV2 {

        @POST("user")
        suspend fun createUser(
            @Body user: User
        ) : UserResponse
}