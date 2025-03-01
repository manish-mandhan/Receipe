package com.manish.mandhan.search.data.repository

import com.manish.mandhan.common.resource.Result
import com.manish.mandhan.search.data.mappers.toDomainModel
import com.manish.mandhan.search.data.remote.SearchRecipeApi
import com.manish.mandhan.search.domain.model.DomainRecipeModel
import com.manish.mandhan.search.domain.repository.SearchRecipeRepository

class SearchRecipeRepositoryImpl(private val api: SearchRecipeApi) : SearchRecipeRepository {

    override suspend fun getRecipeByName(str: String): Result<List<DomainRecipeModel>> {
        return api.getProductByName(str).meals?.let {
            Result.Success(it.toDomainModel())
        } ?: Result.Error(message = "No Results Found")
    }
}