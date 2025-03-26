package com.manish.mandhan.presentation.screens.recipe_list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manish.mandhan.search.domain.resource.NetworkResult
import com.manish.mandhan.common.resource.UiText
import com.manish.mandhan.search.domain.model.DomainRecipeModel
import com.manish.mandhan.search.domain.model.FavoriteModel
import com.manish.mandhan.search.domain.use_case.FetchListOfFavoriteRecipesByListIDsUseCase
import com.manish.mandhan.search.domain.use_case.GetAllRecipesUseCase
import com.manish.mandhan.search.domain.use_case.ToggleFavoriteRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val toggleFavoriteRecipeUseCase: ToggleFavoriteRecipeUseCase,
    private val fetchListOfFavoriteRecipesByListIDsUseCase: FetchListOfFavoriteRecipesByListIDsUseCase
) : ViewModel() {



    private val _uiState = MutableStateFlow(RecipeList.UiState())
    val uiState = _uiState.asStateFlow()

    val favoriteItems = MutableStateFlow(emptyList<FavoriteModel>())

    private val searchQuery = MutableSharedFlow<String>()

    init {
        Log.d("PROFILE_LOG", "init blocked called of homeVM")
        _uiState.value = RecipeList.UiState(text = UiText.RemoteString("Search Anything"))
        search()

    }

    fun onEvent(event: RecipeList.Event) {
        when (event) {
            is RecipeList.Event.SearchRecipe -> {
                viewModelScope.launch {
                    searchQuery.emit(event.q)
                }
            }
        }

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun search() {
        searchQuery.flatMapLatest {
            _uiState.value = RecipeList.UiState(isLoading = true, text = UiText.Idle)

            getAllRecipesUseCase(it)

        }.onEach { result ->
            when (result) {
                NetworkResult.Loading -> {
                    _uiState.value = RecipeList.UiState(isLoading = true, text = UiText.Idle)
                }

                is NetworkResult.Error -> {
                    _uiState.value = RecipeList.UiState(text = UiText.RemoteString(result.message))
                }

                is NetworkResult.NoNetwork -> {
                    _uiState.value = RecipeList.UiState(text = UiText.RemoteString(result.message))
                }

                is NetworkResult.Success -> {
                    val ids = result.data.map { it.idMeal }

                    fetchListOfFavoriteRecipesByListIDsUseCase(ids).onEach {
                        favoriteItems.value = it
                    }.launchIn(viewModelScope)

                    _uiState.value = RecipeList.UiState(data = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun toggleFavorite(id: String) {
        viewModelScope.launch {
            toggleFavoriteRecipeUseCase(id)
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