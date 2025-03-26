package com.manish.mandhan.search.domain.use_case

import com.manish.mandhan.search.domain.model.FavoriteModel
import com.manish.mandhan.search.domain.repository.FavoriteRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FetchListOfFavoriteRecipesByListIDsUseCase @Inject constructor(private val favoriteRecipeRepository: FavoriteRecipeRepository) {

    operator fun invoke(ids: List<String>): Flow<List<FavoriteModel>> {
        return favoriteRecipeRepository.getListOfItemsByListOfIds(ids)
    }

}