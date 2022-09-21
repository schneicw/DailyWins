package com.schneider.dailywins.network

import com.schneider.dailywins.responses.LoginResponse
import com.schneider.dailywins.responses.User
import retrofit2.Call
import retrofit2.http.*


interface AuthApi {

    @FormUrlEncoded
    @POST
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("returnSecureToken") returnToken: Boolean = true
    ) : LoginResponse


    // 1. put email, pass, token in login body object
}