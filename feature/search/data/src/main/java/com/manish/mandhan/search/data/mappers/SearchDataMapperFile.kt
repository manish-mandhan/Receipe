package com.manish.mandhan.search.data.mappers

import com.manish.mandhan.search.data.model.OfflineRecipeDTO
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

fun RecipeDTO.toOfflineModel(strMealNonNull: String): OfflineRecipeDTO {
    return OfflineRecipeDTO(
        dateModified = dateModified,
        idMeal = idMeal,
        strArea = strArea,
        strCategory = strCategory,
        strCreativeCommonsConfirmed = strCreativeCommonsConfirmed,
        strDrinkAlternate = strDrinkAlternate,
        strImageSource = strImageSource,
        strIngredient1 = strIngredient1,
        strIngredient2 = strIngredient2,
        strIngredient3 = strIngredient3,
        strIngredient4 = strIngredient4,
        strIngredient5 = strIngredient5,
        strIngredient6 = strIngredient6,
        strIngredient7 = strIngredient7,
        strIngredient8 = strIngredient8,
        strIngredient9 = strIngredient9,
        strIngredient10 = strIngredient10,
        strIngredient11 = strIngredient11,
        strIngredient12 = strIngredient12,
        strIngredient13 = strIngredient13,
        strIngredient14 = strIngredient14,
        strIngredient15 = strIngredient15,
        strIngredient16 = strIngredient16,
        strIngredient17 = strIngredient17,
        strIngredient18 = strIngredient18,
        strIngredient19 = strIngredient19,
        strIngredient20 = strIngredient20,
        strInstructions = strInstructions,
        strMealId = strMealNonNull, // Ensuring a non-nullable value for PrimaryKey
        strMealThumb = strMealThumb,
        strMeasure1 = strMeasure1,
        strMeasure2 = strMeasure2,
        strMeasure3 = strMeasure3,
        strMeasure4 = strMeasure4,
        strMeasure5 = strMeasure5,
        strMeasure6 = strMeasure6,
        strMeasure7 = strMeasure7,
        strMeasure8 = strMeasure8,
        strMeasure9 = strMeasure9,
        strMeasure10 = strMeasure10,
        strMeasure11 = strMeasure11,
        strMeasure12 = strMeasure12,
        strMeasure13 = strMeasure13,
        strMeasure14 = strMeasure14,
        strMeasure15 = strMeasure15,
        strMeasure16 = strMeasure16,
        strMeasure17 = strMeasure17,
        strMeasure18 = strMeasure18,
        strMeasure19 = strMeasure19,
        strMeasure20 = strMeasure20,
        strSource = strSource,
        strTags = strTags,
        strYoutube = strYoutube,
        strMeal = strMeal
    )
}

fun OfflineRecipeDTO.toOnlineModel(): RecipeDTO {
    return RecipeDTO(
        dateModified = dateModified,
        idMeal = idMeal,
        strArea = strArea,
        strCategory = strCategory,
        strCreativeCommonsConfirmed = strCreativeCommonsConfirmed,
        strDrinkAlternate = strDrinkAlternate,
        strImageSource = strImageSource,
        strIngredient1 = strIngredient1,
        strIngredient2 = strIngredient2,
        strIngredient3 = strIngredient3,
        strIngredient4 = strIngredient4,
        strIngredient5 = strIngredient5,
        strIngredient6 = strIngredient6,
        strIngredient7 = strIngredient7,
        strIngredient8 = strIngredient8,
        strIngredient9 = strIngredient9,
        strIngredient10 = strIngredient10,
        strIngredient11 = strIngredient11,
        strIngredient12 = strIngredient12,
        strIngredient13 = strIngredient13,
        strIngredient14 = strIngredient14,
        strIngredient15 = strIngredient15,
        strIngredient16 = strIngredient16,
        strIngredient17 = strIngredient17,
        strIngredient18 = strIngredient18,
        strIngredient19 = strIngredient19,
        strIngredient20 = strIngredient20,
        strInstructions = strInstructions,
        strMeal = strMeal,  // No need for null check since it's already non-null in OfflineRecipeDTO
        strMealThumb = strMealThumb,
        strMeasure1 = strMeasure1,
        strMeasure2 = strMeasure2,
        strMeasure3 = strMeasure3,
        strMeasure4 = strMeasure4,
        strMeasure5 = strMeasure5,
        strMeasure6 = strMeasure6,
        strMeasure7 = strMeasure7,
        strMeasure8 = strMeasure8,
        strMeasure9 = strMeasure9,
        strMeasure10 = strMeasure10,
        strMeasure11 = strMeasure11,
        strMeasure12 = strMeasure12,
        strMeasure13 = strMeasure13,
        strMeasure14 = strMeasure14,
        strMeasure15 = strMeasure15,
        strMeasure16 = strMeasure16,
        strMeasure17 = strMeasure17,
        strMeasure18 = strMeasure18,
        strMeasure19 = strMeasure19,
        strMeasure20 = strMeasure20,
        strSource = strSource,
        strTags = strTags,
        strYoutube = strYoutube
    )
}