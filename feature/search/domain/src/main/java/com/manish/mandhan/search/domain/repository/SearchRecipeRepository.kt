package com.manish.mandhan.search.domain.repository

import com.manish.mandhan.common.resource.Result
import com.manish.mandhan.search.domain.model.DomainRecipeModel

interface SearchRecipeRepository {

    suspend fun getRecipeByName(str: String): Result<List<DomainRecipeModel>>
}