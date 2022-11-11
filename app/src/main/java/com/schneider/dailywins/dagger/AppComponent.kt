package com.schneider.dailywins.dagger

import com.schneider.dailywins.DailyWinsApplication
import com.schneider.dailywins.home.HomeFragmentModule
import com.schneider.dailywins.login.LoginFragmentModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@ApplicationScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        LoginFragmentModule::class,
        ActivityModule::class,
        HomeFragmentModule::class,
        ContextModule::class
    ]
)
interface AppComponent : AndroidInjector<DailyWinsApplication> {
}