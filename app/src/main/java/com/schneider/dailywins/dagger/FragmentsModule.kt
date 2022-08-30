package com.schneider.dailywins.dagger

import com.schneider.dailywins.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity() : LoginFragment
}