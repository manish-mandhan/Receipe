package com.manish.mandhan.search.data.submodule.favorite.repository

import com.manish.mandhan.search.data.submodule.favorite.local.FavoriteApi
import com.manish.mandhan.search.data.submodule.favorite.mapper.domainModel
import com.manish.mandhan.common.common_model.FavoriteModelEntity
import com.manish.mandhan.search.domain.model.FavoriteModel
import com.manish.mandhan.search.domain.repository.FavoriteRecipeRepository
import com.manish.mandhan.search.domain.resource.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRecipeRepositoryImpl(private val favoriteApi: FavoriteApi) :
    FavoriteRecipeRepository {

    override suspend fun insertFavorite(id: String): Result {
        return try {
            favoriteApi.insertFavorite(FavoriteModelEntity(id))

            Result.Success(FavoriteModel(id))
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unable to insert")
        }

    }

    override suspend fun isRecipePresent(id: String): FavoriteModel? {
        return favoriteApi.isRecipePresent(id).firstOrNull()?.domainModel()

    }

    override suspend fun deleteFavorite(id: String) {
        favoriteApi.deleteFavorite(FavoriteModelEntity(id))
    }

    override fun getListOfItemsByListOfIds(ids: List<String>): Flow<List<FavoriteModel>> {
        return favoriteApi.getListOfItemsByListOfIds(ids).map { list ->
            list.map {
                it.domainModel()
            }
        }
    }


}