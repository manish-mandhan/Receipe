package com.manish.mandhan.presentation.screens.recipe_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manish.mandhan.common.resource.UiText
import com.manish.mandhan.presentation.screens.recipe_list.RecipeListViewModel.RecipeList
import com.manish.mandhan.search.domain.model.DetailedDomainRecipeModel
import com.manish.mandhan.search.domain.model.DomainRecipeModel
import com.manish.mandhan.search.domain.resource.DetailedNetworkResult
import com.manish.mandhan.search.domain.use_case.GetRecipeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecipeDetails.UiState())
    val uiState = _uiState.asStateFlow()


    private fun getRecipeDetailsById(id: String) {
        viewModelScope.launch {
            getRecipeDetailsUseCase(id).collect {
                when (it) {
                    DetailedNetworkResult.Loading -> {
                        _uiState.value = RecipeDetails.UiState(isLoading = true)
                    }

                    is DetailedNetworkResult.Error -> {
                        _uiState.value =
                            RecipeDetails.UiState(text = UiText.RemoteString(it.message))
                    }

                    is DetailedNetworkResult.NoNetwork -> {
                        _uiState.value =
                            RecipeDetails.UiState(text = UiText.RemoteString(it.message))
                    }

                    is DetailedNetworkResult.Success -> {
                        _uiState.value = RecipeDetails.UiState(data = it.data)
                    }
                }
            }
        }

    }


    object RecipeDetails {
        data class UiState(
            val text: UiText = UiText.Idle,
            val data: DetailedDomainRecipeModel? = null,
            val isLoading: Boolean = false
        )


        sealed class Event {
            data class onBackPress(val q: String) : Event()
        }

        sealed interface Navigation {
            data class GoToRecipeList(val id: String) : Navigation
        }
    }
}