package com.manish.mandhan.search.domain.resource

import com.manish.mandhan.search.domain.model.FavoriteModel

sealed class Result {
    data class Success(val data: FavoriteModel) : Result()
    data class Error(val message: String) : Result()
}