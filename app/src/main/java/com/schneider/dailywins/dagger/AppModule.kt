package com.schneider.dailywins.dagger

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.schneider.dailywins.DailyWinsApplication
import dagger.Module
import dagger.Provides

private val Context.store: DataStore<Preferences> by preferencesDataStore(name = "dailywins-store")

@Module
class AppModule(application: DailyWinsApplication) {

    companion object {


        @Provides
        fun providesFirebaseAuth() : FirebaseAuth = Firebase.auth

        @Provides
        fun providesFirebaseStore() : FirebaseFirestore = Firebase.firestore

        @ApplicationScope
        @Provides
        fun providesDataStore(context: Context) : DataStore<Preferences> = context.store

        @ApplicationScope
        @Provides
        fun provideApplication(application: DailyWinsApplication) : Application = application
    }
}