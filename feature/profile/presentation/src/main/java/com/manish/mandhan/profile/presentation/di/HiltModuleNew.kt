package com.manish.mandhan.profile.presentation.di

import com.manish.mandhan.profile.presentation.navigation.ProfileFeatureNavigationApi
import com.manish.mandhan.profile.presentation.navigation.ProfileFeatureNavigationApiImpl
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