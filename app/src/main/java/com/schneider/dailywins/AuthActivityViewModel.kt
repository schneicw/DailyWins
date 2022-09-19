package com.schneider.dailywins

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.schneider.dailywins.network.AuthApi
import com.schneider.dailywins.network.RemoteDataSource
import com.schneider.dailywins.network.Resource
import com.schneider.dailywins.repository.AuthRepository
import com.schneider.dailywins.responses.LoginResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthActivityViewModel @Inject constructor() : ViewModel() {

    private val remoteDataSource = RemoteDataSource()
    private val repository: AuthRepository = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))
    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    // easy
    private lateinit var auth: FirebaseAuth

    fun login(
            email: String,
            password: String
        ) = viewModelScope.launch {
            _loginResponse.value = repository.login(email, password)
        }
}