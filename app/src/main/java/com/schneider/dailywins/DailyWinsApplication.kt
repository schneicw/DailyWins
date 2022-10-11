package com.schneider.dailywins

import android.app.Application
import com.schneider.dailywins.dagger.AppModule
import com.schneider.dailywins.dagger.ApplicationScope
import com.schneider.dailywins.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

@ApplicationScope
class DailyWinsApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }
}