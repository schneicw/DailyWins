package com.schneider.dailywins.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    // "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyCouwwI5MjkqkFXTvNH2HPEPN-BRpChvcg/"
    companion object {
        private const val BASE_URL =
            "https://identitytoolkit.googleapis.com/v1/accounts:signUp/"
    }

    fun <Api> buildApi(
        api: Class<Api>
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val url = chain
                            .request()
                            .url
                            .newBuilder()
//                            .addQueryParameter("key", "AIzaSyCouwwI5MjkqkFXTvNH2HPEPN-BRpChvcg")
                            .build()
                        chain.proceed(chain.request().newBuilder().url(url).build())
                    }
                    .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}