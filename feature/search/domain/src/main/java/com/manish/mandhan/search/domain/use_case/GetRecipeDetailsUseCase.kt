package com.manish.mandhan.search.domain.use_case

import com.manish.mandhan.common.resource.NetworkException
import com.manish.mandhan.search.domain.resource.NetworkResult
import com.manish.mandhan.common.resource.Result
import com.manish.mandhan.search.domain.repository.SearchRecipeRepository
import com.manish.mandhan.search.domain.resource.DetailedNetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecipeDetailsUseCase @Inject constructor(val repository: SearchRecipeRepository) {
    operator fun invoke(id: String): Flow<DetailedNetworkResult> {
        return flow {
            emit(DetailedNetworkResult.Loading)

            try {
                val response = repository.getRecipeDetail(id)
                when (response) {
                    is Result.Error -> emit(
                        DetailedNetworkResult.Error(
                            response.message ?: "Unknown error occurred from Try"
                        )
                    )

                    is Result.Success -> emit(DetailedNetworkResult.Success(response.data!!))
                }
            } catch (e: NetworkException) {
                emit(DetailedNetworkResult.NoNetwork(e.message ?: "No Network From Catch-Try"))
            }


        }
    }

}