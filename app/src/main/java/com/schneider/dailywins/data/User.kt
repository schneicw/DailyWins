package com.schneider.dailywins.data

data class User (
    val email: String,
    val password: String,
    val returnSecureToken: Boolean
)