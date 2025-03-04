package com.manish.mandhan.presentation.screens.recipe_list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.manish.mandhan.presentation.screens.recipe_list.components.MySearchBar
import com.manish.mandhan.presentation.screens.recipe_list.components.RecipeItem
import com.manish.mandhan.presentation.screens.theme.onWhite
import com.manish.mandhan.search.domain.model.DomainRecipeModel
import kotlinx.coroutines.flow.StateFlow


const val TAG = "DEBUGGING LOG"

@Composable
fun RecipeListScreen(
    uiState: StateFlow<RecipeListViewModel.RecipeList.UiState>,
    onItemClick: (data: DomainRecipeModel) -> Unit,
    onSearch: (s: String) -> Unit
) {
    val state = uiState.collectAsState()
    val text = state.value.text.getString(LocalContext.current)

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            MySearchBar(onSearch = onSearch)


            if (text.isNotEmpty()) {
                Spacer(modifier = Modifier.fillMaxHeight(.4f))
                Text(
                    text = text,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .zIndex(1f)
                )
            }
            if (state.value.isLoading) {
                Spacer(modifier = Modifier.fillMaxHeight(.4f))
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .zIndex(1f)
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(top = 16.dp),
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {
                items(state.value.data ?: emptyList()) {
                    RecipeItem(data = it, onItemClick = onItemClick)
                }
            }

        }
    }


}




