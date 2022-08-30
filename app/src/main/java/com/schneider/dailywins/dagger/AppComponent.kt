package com.schneider.dailywins.dagger

import com.schneider.dailywins.DailyWinsApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<DailyWinsApplication> {

}