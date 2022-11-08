package com.schneider.dailywins.login

import com.google.firebase.auth.FirebaseUser

interface AuthenticationRepository {

    fun getUser() : FirebaseUser?

    fun authentication(username : String, password : String)

    fun refreshAuthToken(refreshToken: String)

    fun logout() : Unit
}