package com.schneider.dailywins.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    companion object {
        private const val BASE_URL =
            "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyCouwwI5MjkqkFXTvNH2HPEPN-BRpChvcg"
    }

    fun <Api> buildApi(
        api: Class<Api>
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}