package com.schneider.dailywins.dagger

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(context: Context) {

    private val context: Context

    @Provides
    fun getContext(): Context {
        return context
    }

    init {
        this.context = context
    }
}