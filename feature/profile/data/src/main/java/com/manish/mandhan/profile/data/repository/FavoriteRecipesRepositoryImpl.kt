package com.manish.mandhan.profile.data.repository

import com.manish.mandhan.common.common_model.FavoriteModelEntity
import com.manish.mandhan.profile.data.local.FavoriteRecipeDao
import com.manish.mandhan.profile.data.mapper.toDomain
import com.manish.mandhan.profile.data.mapper.toFavoriteRecipeModel
import com.manish.mandhan.profile.data.remote.FavoriteRecipeApi
import com.manish.mandhan.profile.domain.model.FavoriteModel
import com.manish.mandhan.profile.domain.model.FavoriteRecipeModel
import com.manish.mandhan.profile.domain.repository.FavoriteRecipesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRecipesRepositoryImpl(
    private val favoriteRecipeDao: FavoriteRecipeDao,
    private val favoriteRecipeApi: FavoriteRecipeApi
) : FavoriteRecipesRepository {
    override fun getFavoriteRecipesIds(): Flow<List<FavoriteModel>> {

        return favoriteRecipeDao.getAllRecipeIds().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }


    override suspend fun getFavoriteRecipesById(ids: List<String>): List<FavoriteRecipeModel> {
        return ids.mapNotNull { id ->

            val response = favoriteRecipeApi.getProductByName(id).meals ?: emptyList()
            response.continueIf { it.isNotEmpty() }?.first()?.toFavoriteRecipeModel()

        }

    }

    override suspend fun deleteFavoriteRecipe(id: String) {
        favoriteRecipeDao.deleteFavoriteRecipe(id)
    }

    override suspend fun insertFavoriteRecipe(id: String) {
        favoriteRecipeDao.insertFavoriteRecipe(FavoriteModelEntity(id = id))
    }

    override suspend fun isFavoriteRecipeAvailable(id: String): Boolean {
        return favoriteRecipeDao.isFavoriteRecipePresent(id) != null
    }


}

fun <T> List<T>.continueIf(block: (List<T>) -> Boolean): List<T>? {
    val bool = block(this)
    return if (bool) {
        this
    } else {
        null
    }
}