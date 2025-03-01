package com.manish.mandhan.search.data.repository

import com.manish.mandhan.common.resource.NetworkException
import com.manish.mandhan.common.resource.Result
import com.manish.mandhan.search.data.mappers.toDetailedDomainModel
import com.manish.mandhan.search.data.mappers.toDomainModel
import com.manish.mandhan.search.data.remote.SearchRecipeApi
import com.manish.mandhan.search.domain.model.DetailedDomainRecipeModel
import com.manish.mandhan.search.domain.model.DomainRecipeModel
import com.manish.mandhan.search.domain.repository.SearchRecipeRepository
import java.net.UnknownHostException

class SearchRecipeRepositoryImpl(val api: SearchRecipeApi) : SearchRecipeRepository {
    override suspend fun getRecipeByName(str: String): Result<List<DomainRecipeModel>> {
        try {
            return api.getProductByName(str).meals?.let {
                Result.Success(it.toDomainModel())
            } ?: Result.Error(message = "Something went wrong")
        } catch (e: Exception) {
            if (e is UnknownHostException) {
                throw NetworkException()
            }
            return Result.Error(message = e.message)
        }
    }

    override suspend fun getRecipeDetail(id: String): Result<DetailedDomainRecipeModel> {
        val response = api.getProductDetails(id)
        return try {
            response.meals?.let {
                if (it.isNotEmpty()) {
                    Result.Success((it.first().toDetailedDomainModel()))
                } else {
                    Result.Error(message = "Item not found")
                }
            } ?: Result.Error(message = "Something went wrong")
        } catch (e: Exception) {
            if (e is UnknownHostException){
                throw NetworkException()
            }

            Result.Error(message = e.message)
        }
    }
}