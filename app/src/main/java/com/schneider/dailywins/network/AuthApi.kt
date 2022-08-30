package com.schneider.dailywins.network

import com.schneider.dailywins.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("returnSecureToken") returnToken: Boolean
    ) : LoginResponse
}