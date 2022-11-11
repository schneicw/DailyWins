package com.schneider.dailywins.addwins

import com.schneider.dailywins.dagger.FragmentScope
import com.schneider.dailywins.home.DailyWinAdapter
import com.schneider.dailywins.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AddWinModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesAddWinFragment() : AddWinFragment
}