package com.schneider.dailywins

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    // variables

    // inject MainAcitivtyViewModel

    // class ovverides

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // register observer to viewstate (logged in?) load fragment
        // logic itself in mainactivityviewmodel

        // Fragment container view to load fragments
        // otherwise with fragment manager
    }

    // public methods

    // private methods

}