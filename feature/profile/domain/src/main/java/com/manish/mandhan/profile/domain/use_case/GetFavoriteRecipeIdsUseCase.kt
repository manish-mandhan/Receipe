package com.manish.mandhan.profile.domain.use_case

import com.manish.mandhan.profile.domain.repository.FavoriteRecipesRepository
import javax.inject.Inject

class GetFavoriteRecipeIdsUseCase @Inject constructor(
    private val favoriteRecipesRepository: FavoriteRecipesRepository
) {

    operator fun invoke() = favoriteRecipesRepository.getFavoriteRecipesIds()
}