package com.schneider.dailywins.calendar

import com.schneider.dailywins.dagger.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CalendarFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesCalendarFragment() : CalendarFragment
}