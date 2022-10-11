package com.schneider.dailywins.data.state

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.schneider.dailywins.responses.UserResponse

sealed class ApplicationState {

    object UnauthenticatedState : ApplicationState()

    data class AuthenticatedState(
        val user: FirebaseUser
    ) : ApplicationState()
}