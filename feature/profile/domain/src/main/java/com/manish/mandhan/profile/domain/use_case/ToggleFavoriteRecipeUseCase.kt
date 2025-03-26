package com.manish.mandhan.profile.domain.use_case

import com.manish.mandhan.profile.domain.repository.FavoriteRecipesRepository
import javax.inject.Inject


class ToggleFavoriteRecipeUseCase @Inject constructor(
    private val repository: FavoriteRecipesRepository
) {
    suspend operator fun invoke(id: String) {
        if (repository.isFavoriteRecipeAvailable(id)) {
            repository.deleteFavoriteRecipe(id)
        } else {
            repository.insertFavoriteRecipe(id)
        }
    }
}