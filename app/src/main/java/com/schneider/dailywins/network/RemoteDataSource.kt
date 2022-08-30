package com.schneider.dailywins.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    companion object {
        private const val BASE_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=[API_KEY]"
    }

        fun<Api> buildApi(
            api: Class<Api>
        ) : Api {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api)
        }
//    auth = Firebase.auth
//
//
//    auth.createUserWithEmailAndPassword(email, password)
//    .addOnCompleteListener(this) { task ->

//    }
}