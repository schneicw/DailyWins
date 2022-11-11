package com.schneider.dailywins.home

import com.schneider.dailywins.dagger.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesHomeFragment() : HomeFragment

    @ContributesAndroidInjector
    abstract fun contributesAdapter() : DailyWinAdapter
}
