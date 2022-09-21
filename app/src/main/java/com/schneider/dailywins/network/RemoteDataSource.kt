package com.schneider.dailywins.network

import android.util.Log
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
                    .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                    .addInterceptor { chain ->
//                        Log.d("RemoteDataSource", "buildApi() called with: chain = $chain")
//                        val request = chain.request()
//                        val orginalURL = request.url
//                        val newBuilder = orginalURL.newBuilder().addQueryParameter("key", "AIzaSyCouwwI5MjkqkFXTvNH2HPEPN-BRpChvcg")
//                        val url = newBuilder.build()
//                        Log.d("RemoteDataSource","$url")
//                        val requestBuilder = request.newBuilder().url(url)
//                        chain.proceed(requestBuilder.build())
                        Log.d("RemoteDataSource", "Hello World")
                        chain.proceed(chain.request())
                    }
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}