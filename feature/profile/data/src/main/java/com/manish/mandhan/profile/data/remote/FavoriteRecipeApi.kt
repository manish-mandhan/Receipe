package com.manish.mandhan.profile.data.remote

import com.manish.mandhan.profile.data.model.RecipeResponseModel
import retrofit2.http.GET
import retrofit2.http.Query


interface FavoriteRecipeApi {

    @GET("api/json/v1/1/lookup.php")
    suspend fun getProductByName(@Query("i") s: String): RecipeResponseModel

}