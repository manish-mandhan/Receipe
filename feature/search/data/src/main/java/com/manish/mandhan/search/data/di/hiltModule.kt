package com.manish.mandhan.search.data.di

import com.manish.mandhan.search.data.remote.SearchRecipeApi
import com.manish.mandhan.search.data.repository.SearchRecipeRepositoryImpl
import com.manish.mandhan.search.domain.repository.SearchRecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object hiltModule {
    private const val BASE_URL = "https://www.themealdb.com/"

    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesRecipeApi(retrofit: Retrofit): SearchRecipeApi {
        return retrofit.create(SearchRecipeApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideSearchRecipeRepoImpl(api: SearchRecipeApi): SearchRecipeRepository {
//        return SearchRecipeRepositoryImpl(api)
//    }


}

