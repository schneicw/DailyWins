package com.schneider.dailywins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schneider.dailywins.network.RetrofitService
import com.schneider.dailywins.network.Resource
import com.schneider.dailywins.network.UserServiceV2
import com.schneider.dailywins.repository.UserRepositoryV2
import com.schneider.dailywins.responses.UserResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthActivityViewModel @Inject constructor() : ViewModel() {

    private val retrofitService = RetrofitService()

    private val repositoryV2: UserRepositoryV2 = UserRepositoryV2(retrofitService.buildApi(UserServiceV2::class.java))
    private val _loginResponse2 : MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val loginResponse2: LiveData<Resource<UserResponse>>
        get() = _loginResponse2

    fun login2(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse2.value = repositoryV2.login(email, password)
    }
}