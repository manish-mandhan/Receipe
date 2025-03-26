package com.manish.mandhan.search.data.mappers

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.manish.mandhan.search.data.model.OfflineRecipeDTO
import com.manish.mandhan.search.data.model.RecipeDTO

class DTOConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromOfflineRecipeDTO(value: OfflineRecipeDTO): String {
        return gson.toJson(value) // Convert List<RecipeDTO> to JSON string
    }

    @TypeConverter
    fun toOfflineRecipeDTO(value: String): OfflineRecipeDTO {
        return gson.fromJson(value, OfflineRecipeDTO::class.java) // Convert JSON string back to List<RecipeDTO>
    }
}