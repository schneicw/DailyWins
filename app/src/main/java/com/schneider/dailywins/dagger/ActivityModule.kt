package com.schneider.dailywins.dagger

import com.schneider.dailywins.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
        ]
    )
    abstract fun contributesMainActivity() : MainActivity
}