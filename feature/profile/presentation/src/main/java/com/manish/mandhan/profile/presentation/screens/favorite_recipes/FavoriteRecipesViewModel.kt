package com.manish.mandhan.profile.presentation.screens.favorite_recipes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manish.mandhan.profile.domain.model.FavoriteRecipeModel
import com.manish.mandhan.profile.domain.resource.FavoriteRecipeResult
import com.manish.mandhan.profile.domain.use_case.GetFavoriteRecipeIdsUseCase
import com.manish.mandhan.profile.domain.use_case.GetFavoriteRecipesByIdsUseCase
import com.manish.mandhan.profile.domain.use_case.ToggleFavoriteRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteRecipesViewModel @Inject constructor(
    getFavoriteRecipeIdsUseCase: GetFavoriteRecipeIdsUseCase,
    private val getFavoriteRecipesByIdsUseCase: GetFavoriteRecipesByIdsUseCase,
    private val toggleFavoriteRecipeUseCase: ToggleFavoriteRecipeUseCase
) : ViewModel() {


    private var currentJob: Job? = null
    private val _offlineIds = MutableStateFlow(emptyList<String>())

    private val _uiEvent: MutableSharedFlow<String> = MutableSharedFlow(replay = 0)
    val uiEvent: SharedFlow<String> = _uiEvent.asSharedFlow()


    private val _uiState = MutableStateFlow(Utils.UiState(emptyList()))
    val uiState = _uiState.asStateFlow()

    init {
        Log.d("PROFILE_LOG", "init block called of VM")

        getFavoriteRecipeIdsUseCase.invoke().onEach { ids ->
            _offlineIds.value = ids.map { it.id }
        }.launchIn(viewModelScope)


        _offlineIds.flatMapLatest {

            flow {
                emit(getFavoriteRecipesByIdsUseCase(it))
            }
        }.onEach {

            when (it) {
                is FavoriteRecipeResult.Error -> {
                    _uiEvent.emit(it.message)
                }

                is FavoriteRecipeResult.Success -> {
                    _uiState.value = Utils.UiState(it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun toggleRecipe(id: String) {
        currentJob?.cancel()

        currentJob = viewModelScope.launch {
            toggleFavoriteRecipeUseCase(id)
        }


    }

    object Utils {
        data class UiState(
            val favoriteRecipeModels: List<FavoriteRecipeModel>
        )
    }
}