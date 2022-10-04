package com.schneider.dailywins.login

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.schneider.dailywins.network.RetrofitService
import com.schneider.dailywins.network.Resource
import com.schneider.dailywins.network.UserServiceV2
import com.schneider.dailywins.repository.UserRepositoryV2
import com.schneider.dailywins.responses.UserResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragmentViewModel @Inject constructor() : ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth

    private val _loginResponse : MutableLiveData<AuthResult?> = MutableLiveData()
    val loginResponse: MutableLiveData<AuthResult?>
        get() = _loginResponse

    private val _loading : MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean>
        get() = _loading

    fun loginWithFirebaseSDK(
        email: String,
        password: String) = viewModelScope.launch {
        _loading.value = true
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _loading.value = false
                if (task.isSuccessful) {
                    _loginResponse.value = task.result
                    Log.d("AuthActivityViewModel", "loginUserWithEmail:success")
                } else {
                    _loginResponse.value = null
                    // If sign in fails, display a message to the user.
                    Log.w("AuthActivityViewModel", "loginUserWithEmail:failure", task.exception)
                }
            }
    }
}