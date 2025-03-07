package com.manish.mandhan.feature.profile.di

import com.manish.mandhan.feature.profile.navigation.ProfileFeatureNavigationApi
import com.manish.mandhan.feature.profile.navigation.ProfileFeatureNavigationApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModuleNew {

    @Provides
    @Singleton
    fun provideProfileFeatureNavigationApi(): ProfileFeatureNavigationApi {
        return ProfileFeatureNavigationApiImpl()
    }
}