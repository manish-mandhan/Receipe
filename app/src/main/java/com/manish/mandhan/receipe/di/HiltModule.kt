package com.manish.mandhan.receipe.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manish.mandhan.receipe.App
import com.manish.mandhan.search.data.local.RecipeDao
import com.manish.mandhan.search.data.local.RecipeRoomDatabase
import com.manish.mandhan.search.data.remote.SearchRecipeApi
import com.manish.mandhan.search.data.repository.SearchRecipeRepositoryImpl
import com.manish.mandhan.search.domain.repository.SearchRecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    private const val RECIPE_ROOM_DATA_BASE_NAME = "recipe_room_database"
    private const val BASE_URL = "https://www.themealdb.com/"

    @Provides
    @Singleton
    fun provideOkHttpInstance() = OkHttpClient.Builder()
        .connectTimeout(timeout = 15, unit = TimeUnit.SECONDS)
        .readTimeout(timeout = 15, unit = TimeUnit.SECONDS)
        .build()


    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesRecipeApi(retrofit: Retrofit): SearchRecipeApi {
        return retrofit.create(SearchRecipeApi::class.java)
    }


    @Singleton
    @Provides
    fun providesRoomInstance(app: Application): RecipeRoomDatabase {
        return Room.databaseBuilder(
            app,
            RecipeRoomDatabase::class.java,
            name = "recipe_room_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesRecipeDao(recipeRoomDatabase: RecipeRoomDatabase): RecipeDao {
        return recipeRoomDatabase.recipeDao()
    }


    @Provides
    @Singleton
    fun providesRepoToSearchData(
        searchRecipeApi: SearchRecipeApi,
        dao: RecipeDao
    ): SearchRecipeRepository {
        return SearchRecipeRepositoryImpl(searchRecipeApi, dao)
    }
}