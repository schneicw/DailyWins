package com.schneider.dailywins.login

import com.schneider.dailywins.dagger.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesLoginFragment() : LoginFragment
}
