package com.manish.mandhan.feature.settings.di

import com.manish.mandhan.feature.settings.SettingsFeatureNavigationApi
import com.manish.mandhan.feature.settings.SettingsFeatureNavigationApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileFeatureHiltModule {

    @Provides
    @Singleton
    fun provideSettingsFeatureNavigationApiImpl(): SettingsFeatureNavigationApi {
        return SettingsFeatureNavigationApiImpl()
    }
}