package com.manish.mandhan.profile.data.di

import com.manish.mandhan.profile.data.local.FavoriteRecipeDao
import com.manish.mandhan.profile.data.remote.FavoriteRecipeApi
import com.manish.mandhan.profile.data.repository.FavoriteRecipesRepositoryImpl
import com.manish.mandhan.profile.domain.repository.FavoriteRecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FeatureProfileDataHiltModule {


    @Provides
    @Singleton
    fun providesFavoriteRecipesImpl(favoriteRecipeDao: FavoriteRecipeDao, favoriteRecipeApi: FavoriteRecipeApi): FavoriteRecipesRepository {
        return FavoriteRecipesRepositoryImpl(favoriteRecipeDao, favoriteRecipeApi)
    }
}