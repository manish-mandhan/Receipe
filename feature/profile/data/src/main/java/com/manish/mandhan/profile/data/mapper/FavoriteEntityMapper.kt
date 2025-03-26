package com.manish.mandhan.profile.data.mapper

import com.manish.mandhan.common.common_model.FavoriteModelEntity
import com.manish.mandhan.profile.data.model.RecipeDTO
import com.manish.mandhan.profile.data.model.RecipeResponseModel
import com.manish.mandhan.profile.domain.model.FavoriteModel
import com.manish.mandhan.profile.domain.model.FavoriteRecipeModel

fun FavoriteModelEntity.toDomain(): FavoriteModel {
    return FavoriteModel(this.id)
}

fun RecipeDTO.toFavoriteRecipeModel(): FavoriteRecipeModel {

    return FavoriteRecipeModel(
        dateModified = dateModified ?: "",
        strYoutube = strYoutube ?: "",
        strMeal = strMeal ?: "",
        strSource = strSource ?: "",
        strCategory = strCategory ?: "",
        strArea = strArea ?: "",
        idMeal = idMeal ?: "",
        strMealThumb = strMealThumb ?: "",
        strInstruction = strInstructions ?: "",
        strTags = strTags ?: "",
        strImageSource = strImageSource ?: "",
        strIngredients = getIngredientsPair()
    )
}

private fun RecipeDTO.getIngredientsPair(): List<Pair<String, String>> {
    val ingredients = listOf(
        strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5,
        strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10,
        strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15,
        strIngredient16, strIngredient17, strIngredient18, strIngredient19, strIngredient20
    )

    val measures = listOf(
        strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5,
        strMeasure6, strMeasure7, strMeasure8, strMeasure9, strMeasure10,
        strMeasure11, strMeasure12, strMeasure13, strMeasure14, strMeasure15,
        strMeasure16, strMeasure17, strMeasure18, strMeasure19, strMeasure20
    )

    return ingredients.zip(measures) { ingredient, measure ->
        Pair(ingredient ?: "", measure ?: "")
    }
}
