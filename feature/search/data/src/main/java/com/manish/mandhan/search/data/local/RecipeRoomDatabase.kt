package com.manish.mandhan.search.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.manish.mandhan.search.data.mappers.AnyTypeConverter
import com.manish.mandhan.search.data.mappers.Converter
import com.manish.mandhan.search.data.mappers.DTOConverter
import com.manish.mandhan.search.data.model.OfflineRecipeDTO
import com.manish.mandhan.search.data.model.OfflineRecipeResponseModel

@Database(entities = [OfflineRecipeDTO::class], version = 3, exportSchema = false)
@TypeConverters(AnyTypeConverter::class)
abstract class RecipeRoomDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

}