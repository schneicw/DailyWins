package com.schneider.dailywins.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

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
                        val request = chain.request()
                        val orginalURL = request.url
//                        val newBuilder = orginalURL.newBuilder().addQueryParameter("key", "AIzaSyCouwwI5MjkqkFXTvNH2HPEPN-BRpChvcg")
//                        val url = newBuilder.build()
                        val temp = orginalURL.newBuilder().build()
                        val requestBuilder = request.newBuilder().url(temp)
                        chain.proceed(requestBuilder.build())
                    }
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}