package com.schneider.dailywins.dagger

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.schneider.dailywins.MainActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    companion object {
        @Provides
        fun providesFirebaseAuth() : FirebaseAuth = Firebase.auth
    }

    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
        ]
    )
    abstract fun contributesMainActivity() : MainActivity
}