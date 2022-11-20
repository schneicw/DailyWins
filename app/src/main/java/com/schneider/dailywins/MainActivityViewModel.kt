package com.schneider.dailywins

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.schneider.dailywins.data.state.ApplicationState
import com.schneider.dailywins.data.store.ApplicationStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val datastore: DataStore<Preferences>,
    private val applicationStore: ApplicationStore,
    private val auth: FirebaseAuth,
    ) {

    private var appState: String?

    init {
        runBlocking {
//            datastore.edit { it.clear() }
            appState = getString("email")
        }

        when (appState) {
            "no email" -> {
                applicationStore.updateApplicationState(ApplicationState.UnauthenticatedState)
            }
            else -> {
                println("MainActivityViewModel - user: $appState , ${auth.currentUser?.email}")
                applicationStore.updateApplicationState(ApplicationState.AuthenticatedState(auth.currentUser))
            }
        }
    }

    private suspend fun getString(key: String): String {
        val prefKey = stringPreferencesKey(key)
        val flow = datastore.data.map { preferences ->
            preferences[prefKey] ?: "no email"
        }
        return flow.first()
    }
}