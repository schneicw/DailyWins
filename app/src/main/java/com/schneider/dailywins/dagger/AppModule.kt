package com.schneider.dailywins.dagger

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.schneider.dailywins.DailyWinsApplication
import com.schneider.dailywins.MainActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
class AppModule(application: DailyWinsApplication) {

    companion object {
        @Provides
        fun providesFirebaseAuth() : FirebaseAuth = Firebase.auth

        @ApplicationScope
        @Provides
        fun provideApplication(application: DailyWinsApplication) : Application = application
    }
}