package com.manish.mandhan.receipe.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.manish.mandhan.profile.data.local.FavoriteRecipeDao
import com.manish.mandhan.search.data.local.RecipeDao
import com.manish.mandhan.search.data.mappers.AnyTypeConverter
import com.manish.mandhan.search.data.model.OfflineRecipeDTO
import com.manish.mandhan.search.data.submodule.favorite.local.FavoriteApi
import com.manish.mandhan.common.common_model.FavoriteModelEntity

@Database(
    entities = [OfflineRecipeDTO::class, FavoriteModelEntity::class],
    version = 6,
    exportSchema = false
)
@TypeConverters(AnyTypeConverter::class)
abstract class RecipeRoomDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun favoriteRecipeDao(): FavoriteRecipeDao
    abstract fun favoriteApi(): FavoriteApi

}