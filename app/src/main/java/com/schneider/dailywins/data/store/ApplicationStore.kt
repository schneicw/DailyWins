package com.schneider.dailywins.data.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.schneider.dailywins.dagger.ApplicationScope
import javax.inject.Inject
import javax.inject.Singleton

class ApplicationStore @Inject constructor() {

    private val _user : MutableLiveData<FirebaseUser?> = MutableLiveData()
    val user: MutableLiveData<FirebaseUser?>
        get() = _user

    fun updateUser(user: FirebaseUser?) {
        _user.value = user
    }
}