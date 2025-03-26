package com.manish.mandhan.search.domain.resource

import com.manish.mandhan.search.domain.model.DomainRecipeModel

sealed class NetworkResult {
    data class Success(val data: List<DomainRecipeModel>) : NetworkResult()
    data class Error(val message: String) : NetworkResult()
    data class NoNetwork(val message: String) : NetworkResult()
    data object Loading : NetworkResult()

}
