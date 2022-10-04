package com.schneider.dailywins.login

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributesLoginFragment() : LoginFragment
}
