package com.manish.mandhan.presentation.di

import com.manish.mandhan.presentation.navigation.SearchFeatureApi
import com.manish.mandhan.presentation.navigation.SearchFeatureApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object searchPresentationDiModule {

    @Provides
    fun provideSearchFeatureApiImpl(): SearchFeatureApi {
        return SearchFeatureApiImpl()
    }
}