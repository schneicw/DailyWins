package com.schneider.dailywins

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.Navigation
import com.schneider.dailywins.data.state.ApplicationState
import com.schneider.dailywins.data.store.ApplicationStore
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    // variables

    @Inject
    lateinit var viewModel : MainActivityViewModel

    @Inject
    lateinit var applicationStore: ApplicationStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (val appState = applicationStore.applicationState.value) {
            is ApplicationState.UnauthenticatedState -> {
                setContentView(R.layout.activity_main)
            }
            is ApplicationState.AuthenticatedState -> {
                Log.d("Auth Activity"," message : ${appState.user}")
                setContentView(R.layout.fragment_home)
            }
            else -> {}
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}