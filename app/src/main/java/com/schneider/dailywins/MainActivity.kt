package com.schneider.dailywins

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    // variables

    @Inject
    lateinit var viewModel : MainActivityViewModel

    // class ovverides

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // viewModel = MainActivityViewModel() - without dagger

        // register observer to viewstate (logged in?) load fragment
        // logic itself in mainactivityviewmodel

        // Fragment container view to load fragments
        // otherwise with fragment manager
    }

    // public methods

    // private methods

}