package com.manish.mandhan.search.domain.repository

import com.manish.mandhan.search.domain.model.FavoriteModel
import com.manish.mandhan.search.domain.resource.Result
import kotlinx.coroutines.flow.Flow

interface FavoriteRecipeRepository {
    suspend fun insertFavorite(id: String): Result

    suspend fun isRecipePresent(id: String): FavoriteModel?

    suspend fun deleteFavorite(id: String)

    fun getListOfItemsByListOfIds(ids: List<String>): Flow<List<FavoriteModel>>
}