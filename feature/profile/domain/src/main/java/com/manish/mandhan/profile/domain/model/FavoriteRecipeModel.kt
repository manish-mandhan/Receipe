package com.manish.mandhan.profile.domain.model

data class FavoriteRecipeModel(
    val strInstruction: String,
    val strMealThumb: String,
    val strMeal: String,
    val strImageSource: Any,
    val dateModified: Any,
    val idMeal: String,
    val strArea: String,
    val strCategory: String,
    val strSource: Any,
    val strTags: String,
    val strYoutube: String,
    val strIngredients: List<Pair<String, String>>
)