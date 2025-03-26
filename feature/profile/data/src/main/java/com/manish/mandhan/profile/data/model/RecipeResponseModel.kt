package com.manish.mandhan.profile.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class RecipeResponseModel(
    val meals: List<RecipeDTO>? = null
)

@Entity("recipe_search_table")
data class OfflineRecipeResponseModel(
    val meals: List<RecipeDTO>? = null,
    @PrimaryKey
    val query:String
)