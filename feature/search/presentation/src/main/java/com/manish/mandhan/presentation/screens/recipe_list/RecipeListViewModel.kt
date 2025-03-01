package com.manish.mandhan.presentation.screens.recipe_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manish.mandhan.search.domain.resource.NetworkResult
import com.manish.mandhan.common.resource.UiText
import com.manish.mandhan.search.domain.model.DomainRecipeModel
import com.manish.mandhan.search.domain.use_case.GetAllRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getAllRecipesUseCase: GetAllRecipesUseCase
) : ViewModel() {

    private var currentJob: Job? = null

    private val _uiState = MutableStateFlow(RecipeList.UiState())
    val uiState = _uiState.asStateFlow()

    private fun search(s: String) {
        currentJob?.cancel()

        currentJob = getAllRecipesUseCase(s).onEach {
            when (it) {
                NetworkResult.Loading -> {
                    _uiState.value = RecipeList.UiState(isLoading = true)
                }

                is NetworkResult.Error -> {
                    _uiState.value = RecipeList.UiState(text = UiText.RemoteString(it.message))
                }

                is NetworkResult.NoNetwork -> {
                    _uiState.value = RecipeList.UiState(text = UiText.RemoteString(it.message))
                }

                is NetworkResult.Success -> {
                    _uiState.value = RecipeList.UiState(data = it.data)
                }
            }

        }.launchIn(viewModelScope)
    }

    fun onEvent(event: RecipeList.Event) {
        when (event) {
            is RecipeList.Event.SearchRecipe -> {
                search(event.q)
            }
        }

    }


    object RecipeList {
        data class UiState(
            val text: UiText = UiText.Idle,
            val data: List<DomainRecipeModel>? = null,
            val isLoading: Boolean = false
        )


        sealed class Event {
            data class SearchRecipe(val q: String) : Event()
        }

        sealed interface Navigation {
            data class GoToRecipeDetail(val id: String) : Navigation
        }
    }


}