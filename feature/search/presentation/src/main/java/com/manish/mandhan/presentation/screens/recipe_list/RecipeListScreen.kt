package com.manish.mandhan.presentation.screens.recipe_list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.manish.mandhan.presentation.navigation.FAV_TAG
import com.manish.mandhan.presentation.screens.recipe_list.components.MyTopBar
import com.manish.mandhan.presentation.screens.recipe_list.components.RecipeItem
import com.manish.mandhan.search.domain.model.DomainRecipeModel
import com.manish.mandhan.search.domain.model.FavoriteModel
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.log
import kotlin.math.roundToInt


const val TAG = "DEBUGGING LOG"

// base

@Composable
fun RecipeListScreen(
    uiState: StateFlow<RecipeListViewModel.RecipeList.UiState>,
    onItemClick: (data: DomainRecipeModel) -> Unit,
    onSearch: (s: String) -> Unit,
    toggleFavorite: (id: String) -> Unit,
    favoriteItems: State<List<FavoriteModel>>,
    bottomNavBarSize: Int
) {

    val maxOffSet = 0f

    val density = LocalDensity.current


    // inset padding
    val padding = WindowInsets.statusBars.asPaddingValues()

    // state
    val state = uiState.collectAsState()
    val text = state.value.text.getString(LocalContext.current)


    val heightTopBar = remember {
        mutableStateOf(0f)
    }
    val currentOffset = remember {
        mutableStateOf(0f)
    }
    // this offset does not have any bounds unlike current offset
    // because i want title and subTitle to have more offset
    val freeOffSet = remember {
        mutableStateOf(0f)
    }
    val contentDerivedState = remember {
        derivedStateOf {
            freeOffSet.value.coerceIn(-bottomNavBarSize.toFloat(), 0f)
        }
    }


    val scrollConnection = remember {
        object : NestedScrollConnection {

            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                freeOffSet.value = currentOffset.value + delta

                val previousOffSet = currentOffset.value


                with(density) {

                    val derivedState = derivedStateOf {
                        freeOffSet.value.coerceIn(-(130.dp.toPx()), maxOffSet)
                    }

                    currentOffset.value = derivedState.value
                }


                val consumed = currentOffset.value - previousOffSet


                return Offset(0f, consumed)
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollConnection),
    ) {

        MyTopBar(onSearch = onSearch, modifier = Modifier
            .graphicsLayer {
                translationY = currentOffset.value
            }
            .onSizeChanged {
                heightTopBar.value = it.height.toFloat()
            }
            .zIndex(4f)
            .shadow(elevation = 6.dp)
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(padding)
            .padding(bottom = 16.dp),
            gap = {
                Spacer(
                    modifier = Modifier.height(with(density) {
                        (-contentDerivedState.value * .23f).toDp()

                    })
                )
            }

        )

        ShowUiUtils(text = text, state = state)

        LazyColumn(contentPadding = PaddingValues(top = 16.dp),
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    translationY = heightTopBar.value + currentOffset.value
                }
                .padding(bottom = with(density) { (bottomNavBarSize + (heightTopBar.value + currentOffset.value)).toDp() })
                .background(MaterialTheme.colorScheme.primary)
        ) {
            items(state.value.data ?: emptyList()) { item ->

                val isFav = favoriteItems.value.any {
                    it.id == item.idMeal
                }

                Log.d(FAV_TAG, "${item.strMeal} is $isFav favorite")

                RecipeItem(data = item, onItemClick = onItemClick, isFavorite = isFav) {
                    toggleFavorite(it)
                }
            }
        }
    }


}

@Composable
fun ShowUiUtils(
    modifier: Modifier = Modifier,
    text: String,
    state: State<RecipeListViewModel.RecipeList.UiState>
) {
    Box(
        modifier = Modifier
            .zIndex(10f)
            .fillMaxSize()
    ) {
        if (text.isNotEmpty()) {
            Spacer(modifier = Modifier.fillMaxHeight(.4f))
            Text(
                text = text, modifier = Modifier
                    .align(Alignment.Center)
                    .zIndex(1f)
            )
        }
        if (state.value.isLoading) {
            Spacer(modifier = Modifier.fillMaxHeight(.4f))
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .align(Alignment.Center)
                    .zIndex(1f)
            )
        }
    }
}




