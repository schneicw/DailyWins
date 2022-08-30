package com.schneider.dailywins.responses

data class User(
    val idToken: String,
    val email: String,
    val refreshToken: String,
    val expiresIn: String,
    val localId: String
)