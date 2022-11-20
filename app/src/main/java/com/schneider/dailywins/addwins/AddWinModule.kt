package com.schneider.dailywins.addwins

import com.schneider.dailywins.dagger.FragmentScope
import com.schneider.dailywins.home.DailyWinAdapter
import com.schneider.dailywins.home.HomeFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
abstract class AddWinModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesAddWinFragment() : AddWinFragment

    companion object {

        @Provides
        fun providesPhotoService() : PhotoService {
            val okHttpClient = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.unsplash.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(PhotoService::class.java)
        }
    }
}