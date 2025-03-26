package com.manish.mandhan.profile.domain.resource

import com.manish.mandhan.profile.domain.model.FavoriteRecipeModel

sealed class FavoriteRecipeResult(val data: List<FavoriteRecipeModel>, val message: String) {

    class Success(data: List<FavoriteRecipeModel>) : FavoriteRecipeResult(data, "")
    class Error(message: String) : FavoriteRecipeResult(emptyList(), message)

}