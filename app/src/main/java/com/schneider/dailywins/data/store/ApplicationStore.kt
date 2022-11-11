package com.schneider.dailywins.data.store

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.schneider.dailywins.dagger.ApplicationScope
import com.schneider.dailywins.data.state.ApplicationState
import javax.inject.Inject
import javax.inject.Singleton

@ApplicationScope
class ApplicationStore @Inject constructor() {

    private val _applicationState : MutableLiveData<ApplicationState> = MutableLiveData(ApplicationState.UnauthenticatedState)
    val applicationState: MutableLiveData<ApplicationState>
        get() = _applicationState

    fun updateApplicationState(appState: ApplicationState) {
        _applicationState.value = appState
    }
}