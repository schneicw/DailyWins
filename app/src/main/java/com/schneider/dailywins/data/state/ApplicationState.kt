package com.schneider.dailywins.data.state

import com.google.firebase.auth.FirebaseUser

sealed class ApplicationState {

    object UnauthenticatedState : ApplicationState()

    data class AuthenticatedState(
        val user: FirebaseUser?
    ) : ApplicationState()
}