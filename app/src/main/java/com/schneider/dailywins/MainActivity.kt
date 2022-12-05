package com.schneider.dailywins

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.schneider.dailywins.data.state.ApplicationState
import com.schneider.dailywins.data.store.ApplicationStore
import com.schneider.dailywins.databinding.ActivityMainBinding
import com.schneider.dailywins.databinding.FragmentLoginBinding
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

        Log.d("Auth Activity"," message : test")
        println("Test Auth Avt")
        when (val appState = applicationStore.applicationState.value) {
            is ApplicationState.UnauthenticatedState -> {
                setContentView(R.layout.activity_main)
            }
            is ApplicationState.AuthenticatedState -> {
                println("AuthenticatedState")
                setContentView(R.layout.activity_main)
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_loginFragment_to_homeFragment)
            }
            else -> {}
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}