package com.manish.mandhan.profile.domain.use_case

import android.util.Log
import com.manish.mandhan.profile.domain.model.FavoriteRecipeModel
import com.manish.mandhan.profile.domain.repository.FavoriteRecipesRepository
import com.manish.mandhan.profile.domain.resource.FavoriteRecipeResult
import java.net.UnknownHostException
import javax.inject.Inject

const val TAG = "LAZY_LOAD_LOG"

class GetFavoriteRecipesByIdsUseCase @Inject constructor(
    private val favoriteRecipesRepository: FavoriteRecipesRepository
) {


    private var previousIds: List<String> = emptyList()
    private var previousResponse: MutableList<FavoriteRecipeModel> = mutableListOf()


    suspend operator fun invoke(ids: List<String>): FavoriteRecipeResult {
        return try {
            if (previousIds.isNotEmpty()) {

                val newIdsFromOutside = ids.mapNotNull {
                    if (previousIds.contains(it)) {
                        null
                    } else {
                        it
                    }
                }

                Log.d(
                    "HELLO CHAI PEE LOG TAG",
                    "newIds : $newIdsFromOutside\nprevious : $previousIds\nids : $ids"
                )

                previousIds = ids

                // when new item get added the whole fetch will begin
                if (newIdsFromOutside.isNotEmpty()) {
                    val response =
                        favoriteRecipesRepository.getFavoriteRecipesById(newIdsFromOutside)

                    previousResponse.addAll(response)

                    val finalResponse = ids.mapNotNull {
                        previousResponse.find { item ->
                            item.idMeal == it
                        }
                    }
                    previousResponse = finalResponse.toMutableList()


                    FavoriteRecipeResult.Success(finalResponse)
                } else {

                    val finalResponse = ids.mapNotNull {
                        previousResponse.find { item ->
                            item.idMeal == it
                        }
                    }
                    previousResponse = finalResponse.toMutableList()


                    FavoriteRecipeResult.Success(finalResponse)
                }

            } else {

                val response = favoriteRecipesRepository.getFavoriteRecipesById(ids)
                previousResponse = response.toMutableList()

                previousIds = ids
                FavoriteRecipeResult.Success(response)
            }


        } catch (e: Exception) {
            when (e) {
                is UnknownHostException -> FavoriteRecipeResult.Error("No internet!")
                else -> FavoriteRecipeResult.Error("Unknown error")
            }
        }
    }
}