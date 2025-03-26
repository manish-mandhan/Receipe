package com.manish.mandhan.search.domain.use_case

import com.manish.mandhan.search.domain.repository.FavoriteRecipeRepository
import javax.inject.Inject

class ToggleFavoriteRecipeUseCase @Inject constructor(val favoriteRecipeRepository: FavoriteRecipeRepository) {

    suspend operator fun invoke(id: String) {
        if (favoriteRecipeRepository.isRecipePresent(id)?.id == id) {
            favoriteRecipeRepository.deleteFavorite(id)
        } else {
            favoriteRecipeRepository.insertFavorite(id)
        }
    }
}