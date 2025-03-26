package com.manish.mandhan.search.data.remote

import com.manish.mandhan.search.data.model.RecipeResponseModel
import retrofit2.http.GET
import retrofit2.http.Query


// name
// https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata

// details
// https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772

interface SearchRecipeApi {

    @GET("api/json/v1/1/search.php")
    suspend fun getProductByName(@Query("s") s: String): RecipeResponseModel

}