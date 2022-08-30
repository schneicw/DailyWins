package com.schneider.dailywins.dagger

import com.schneider.dailywins.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            FragmentsModule::class
        ]
    )
    abstract fun contributesMainActivity() : MainActivity
}