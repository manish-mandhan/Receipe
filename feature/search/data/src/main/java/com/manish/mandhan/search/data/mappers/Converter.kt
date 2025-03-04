package com.manish.mandhan.search.data.mappers

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.manish.mandhan.search.data.model.RecipeDTO

class Converter {
    private val gson = Gson()

    @TypeConverter
    fun fromRecipeDTOList(value: List<RecipeDTO>?): String {
        return gson.toJson(value) // Convert List<RecipeDTO> to JSON string
    }

    @TypeConverter
    fun toRecipeDTOList(value: String): List<RecipeDTO>? {
        val type = object : TypeToken<List<RecipeDTO>>() {}.type
        return gson.fromJson(value, type) // Convert JSON string back to List<RecipeDTO>
    }
}
