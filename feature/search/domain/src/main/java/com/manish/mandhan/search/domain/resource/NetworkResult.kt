package com.manish.mandhan.search.domain.resource

import com.manish.mandhan.search.domain.model.DetailedDomainRecipeModel
import com.manish.mandhan.search.domain.model.DomainRecipeModel

sealed class NetworkResult {
    data class Success(val data: List<DomainRecipeModel>) : NetworkResult()
    data class Error(val message: String) : NetworkResult()
    data class NoNetwork(val message: String) : NetworkResult()
    data object Loading : NetworkResult()

}
sealed class DetailedNetworkResult {
    data class Success(val data: DetailedDomainRecipeModel) : DetailedNetworkResult()
    data class Error(val message: String) : DetailedNetworkResult()
    data class NoNetwork(val message: String) : DetailedNetworkResult()
    data object Loading : DetailedNetworkResult()

}