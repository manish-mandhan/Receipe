package com.manish.mandhan.search.data.repository

import android.util.Log
import com.manish.mandhan.common.resource.Result
import com.manish.mandhan.search.data.local.RecipeDao
import com.manish.mandhan.search.data.mappers.toDomainModel
import com.manish.mandhan.search.data.mappers.toOfflineModel
import com.manish.mandhan.search.data.mappers.toOnlineModel
import com.manish.mandhan.search.data.model.OfflineRecipeResponseModel
import com.manish.mandhan.search.data.model.RecipeResponseModel
import com.manish.mandhan.search.data.remote.SearchRecipeApi
import com.manish.mandhan.search.domain.model.DomainRecipeModel
import com.manish.mandhan.search.domain.repository.SearchRecipeRepository
import java.net.UnknownHostException
import java.util.Locale

class SearchRecipeRepositoryImpl(private val api: SearchRecipeApi, private val dao: RecipeDao) :

    SearchRecipeRepository {
        override suspend fun getRecipeByName(str: String): Result<List<DomainRecipeModel>> {
        return try {

            api.getProductByName(str).meals?.let { recipeDto ->
                recipeDto.forEach {

                    it.strMeal?.let { strMealNonNull ->
                        dao.insertRecipe(
                            it.toOfflineModel(
                                strMealNonNull.trimIndent().lowercase(Locale.getDefault())
                            )
                        )
                    }
                }

                Result.Success(recipeDto.toDomainModel())

            } ?: run {
                dao.getRecipesBySearch(str)?.takeIf { it.isNotEmpty() }?.let { offlineDtoList ->

                    val onlineDtoList = offlineDtoList.map {
                        it.toOnlineModel()
                    }
                    // i converted offline dto-list that i got from room database to online dto-list because mapper only maps online
                    // dto-list to the model that domain accepts

                    Result.Success(onlineDtoList.toDomainModel())

                } ?: Result.Error(message = "No Result Found")
            }
        } catch (e: Exception) {
            // same logic goes here but we are also addressing if there is no internet then also it should fetch data from room

            dao.getRecipesBySearch(str.lowercase(Locale.getDefault()))?.takeIf { it.isNotEmpty() }?.let { offlineDtoList ->

                val onlineDtoList = offlineDtoList.map {
                    it.toOnlineModel()
                }

                Result.Success(onlineDtoList.toDomainModel())
            } ?: Result.Error(message = "No Network")
        }
    }
}