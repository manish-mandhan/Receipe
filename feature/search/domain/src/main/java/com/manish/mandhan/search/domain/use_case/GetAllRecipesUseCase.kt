package com.manish.mandhan.search.domain.use_case

import com.manish.mandhan.search.domain.resource.NetworkResult
import com.manish.mandhan.common.resource.Result
import com.manish.mandhan.search.domain.repository.SearchRecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException
import javax.inject.Inject

class GetAllRecipesUseCase @Inject constructor(private val repository: SearchRecipeRepository) {
    operator fun invoke(name: String): Flow<NetworkResult> {
        return flow {
            kotlinx.coroutines.delay(500)
            emit(NetworkResult.Loading)

            if (name.length >= 3) {


                when (val response = repository.getRecipeByName(name)) {
                    is Result.Error -> emit(
                        NetworkResult.Error(
                            response.message ?: "Unknown error occurred."
                        )
                    )

                    is Result.Success -> {
                        emit(NetworkResult.Success(response.data!!))
                    }
                }
            } else emit(NetworkResult.Error("Please enter a valid name"))

        }.catch {
            if (it is UnknownHostException) {
                emit(NetworkResult.Error("No Network"))
            } else {
                emit(NetworkResult.Error(it.message ?: "Unknown Error From Catch Operator"))
            }
        }.flowOn(Dispatchers.IO)
    }
}