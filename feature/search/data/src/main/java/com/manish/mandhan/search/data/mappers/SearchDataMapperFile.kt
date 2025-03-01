package com.manish.mandhan.search.data.mappers

import com.manish.mandhan.search.data.model.RecipeDTO
import com.manish.mandhan.search.domain.model.DomainRecipeModel

fun List<RecipeDTO>.toDomainModel(): List<DomainRecipeModel> {
    val list = mutableListOf<DomainRecipeModel>()


    this.mapIndexed { index, data ->
        list.add(
            DomainRecipeModel(
                dateModified = data.dateModified ?: "",
                idMeal = data.idMeal ?: "",
                strArea = data.strArea ?: "",
                strCategory = data.strCategory ?: "",
                strImageSource = data.strImageSource ?: "",
                strMeal = data.strMeal ?: "",
                strMealThumb = data.strMealThumb ?: "",
                strSource = data.strSource ?: "",
                strTags = data.strTags ?: "",
                strInstruction = data.strInstructions ?: "",
                strIngredients = data.getIngredientsPair(),
                strYoutube = data.strYoutube ?: ""
            )
        )

    }

    return list.toList()
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
