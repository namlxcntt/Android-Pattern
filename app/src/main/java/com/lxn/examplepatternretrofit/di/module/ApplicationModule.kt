package com.lxn.examplepatternretrofit.di.module

import android.app.Application
import android.content.Context
import com.lxn.examplepatternretrofit.data.datasource.sharepreference.AppPreferences
import com.lxn.examplepatternretrofit.data.datasource.sharepreference.AppPreferencesImpl
import com.lxn.examplepatternretrofit.di.resources.ResourceServices
import com.lxn.examplepatternretrofit.di.resources.ResourcesServicesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideResourcesProvider(application: Application): ResourceServices {
        return ResourcesServicesImpl(application.resources, application.packageName)
    }

    @Provides
    @Singleton
    internal fun provideAppPreferences(appPreferences: AppPreferencesImpl): AppPreferences {
        return appPreferences
    }
}