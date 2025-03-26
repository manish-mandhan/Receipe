package com.manish.mandhan.search.data.di

import com.manish.mandhan.search.data.submodule.favorite.local.FavoriteApi
import com.manish.mandhan.search.data.submodule.favorite.repository.FavoriteRecipeRepositoryImpl
import com.manish.mandhan.search.domain.repository.FavoriteRecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FRecipeDataHiltModule {


    @Provides
    @Singleton
    fun provideFavoriteRecipesRepository(favoriteApi: FavoriteApi): FavoriteRecipeRepository {
        return FavoriteRecipeRepositoryImpl(favoriteApi)
    }
}