package com.manish.mandhan.profile.domain.repository

import com.manish.mandhan.profile.domain.model.FavoriteModel
import com.manish.mandhan.profile.domain.model.FavoriteRecipeModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRecipesRepository {


    fun getFavoriteRecipesIds(): Flow<List<FavoriteModel>>

    suspend fun getFavoriteRecipesById(ids: List<String>): List<FavoriteRecipeModel>

    suspend fun deleteFavoriteRecipe(id: String)

    suspend fun insertFavoriteRecipe(id: String)

    suspend fun isFavoriteRecipeAvailable(id: String): Boolean
}