package com.schneider.dailywins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schneider.dailywins.network.AuthApi
import com.schneider.dailywins.network.RemoteDataSource
import com.schneider.dailywins.network.Resource
import com.schneider.dailywins.repository.AuthRepository
import com.schneider.dailywins.responses.LoginResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthActivityViewModel  @Inject constructor() : ViewModel() {

    private val remoteDataSource = RemoteDataSource()
    private val repository: AuthRepository = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))
    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
            email: String,
            password: String
        ) = viewModelScope.launch {
            _loginResponse.value = repository.login(email, password)
        }
}