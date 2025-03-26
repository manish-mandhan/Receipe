package com.manish.mandhan.profile.presentation.screens.favorite_recipes

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.manish.mandhan.profile.domain.model.FavoriteRecipeModel
import com.manish.mandhan.profile.presentation.screens.favorite_recipes.components.RecipeItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@Composable
fun FavoriteRecipesScreen(
    uiState: State<FavoriteRecipesViewModel.Utils.UiState>,
    uiEvent: SharedFlow<String>,
    scope: CoroutineScope = rememberCoroutineScope(),
    toggleFavoriteRecipe: (String) -> Unit
) {
    val favoriteRecipeModels = uiState.value.favoriteRecipeModels
    val context = LocalContext.current

    val isVisible = remember {
        mutableStateOf("")
    }



    DisposableEffect(Unit) {
        val job = scope.launch {
            uiEvent.collect {
                Toast.makeText(context, it, Toast.LENGTH_LONG)
            }
        }
        onDispose {
            job.cancel()
        }
    }

    val padding = WindowInsets.statusBars.asPaddingValues()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(padding)
        ) {

            items(favoriteRecipeModels) { it

                // will make the animation :)
                RecipeItem(
                    toggleFavorite = toggleFavoriteRecipe,
                    isFavorite = true,
                    onItemClick = {
                        isVisible.value = it.idMeal
                    },
                    data = it
                )
            }
        }
    }

}