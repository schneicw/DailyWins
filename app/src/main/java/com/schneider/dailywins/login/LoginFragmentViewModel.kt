package com.schneider.dailywins.login

import android.content.SharedPreferences
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.schneider.dailywins.dagger.FragmentScope
import com.schneider.dailywins.data.state.ApplicationState
import com.schneider.dailywins.data.store.ApplicationStore
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Scope

@FragmentScope
class LoginFragmentViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val applicationStore: ApplicationStore,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    data class UserCredentials(val email: String, val password: String)
    private val _userCredentials : MutableLiveData<UserCredentials?> = MutableLiveData()
    val userCredentials: MutableLiveData<UserCredentials?>
        get() = _userCredentials

    private val _loginResponse : MutableLiveData<AuthResult?> = MutableLiveData()
    val loginResponse: MutableLiveData<AuthResult?>
        get() = _loginResponse

    private val _loading : MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean>
        get() = _loading


    private fun storeCredentials(userCredentials : UserCredentials) = viewModelScope.launch {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey("email")] = userCredentials.email
            preferences[stringPreferencesKey("password")] = userCredentials.password
        }
    }

    fun loginWithFirebaseSDK(
        email: String,
        password: String) = viewModelScope.launch {
        _loading.value = true
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _loading.value = false
                if (task.isSuccessful) {
                    _loginResponse.value = task.result
                    storeCredentials(UserCredentials(email, password))
                    applicationStore.updateApplicationState(ApplicationState.AuthenticatedState(auth.currentUser))
                    Log.d("AuthActivityViewModel", "loginUserWithEmail:success")
                } else {
                    _loginResponse.value = null
                    // If sign in fails, display a message to the user.
                    Log.w("AuthActivityViewModel", "loginUserWithEmail:failure", task.exception)
                }
            }
    }
}