package com.manish.mandhan.presentation.navigation

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import com.manish.mandhan.common.navigation.FeatureApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.manish.mandhan.common.navigation.Navigation
import com.manish.mandhan.presentation.screens.recipe_details.RecipeDetailsScreen
import com.manish.mandhan.presentation.screens.recipe_list.RecipeListScreen
import com.manish.mandhan.presentation.screens.recipe_list.RecipeListViewModel
import com.manish.mandhan.presentation.screens.shared.SharedViewModel

interface SearchFeatureApi {
    fun registerNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController,
        sharedViewModel: SharedViewModel,
        bottomNavBarSize: Int
    )
}

const val FAV_TAG = "FAV_LOG"

class SearchFeatureApiImpl : SearchFeatureApi {

    override fun registerNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController,
        sharedViewModel: SharedViewModel,
        bottomNavBarSize: Int
    ) {

        navGraphBuilder.navigation<Navigation.BottomNavigation.SearchFeature.Root>(
            startDestination = Navigation.BottomNavigation.SearchFeature.RecipeList
        ) {


            composable<Navigation.BottomNavigation.SearchFeature.RecipeList> {
                val recipeListViewModel: RecipeListViewModel = hiltViewModel()

                val state = recipeListViewModel.favoriteItems.collectAsStateWithLifecycle()


                Log.d(FAV_TAG, "data : ${state.value.size}")

                RecipeListScreen(
                    uiState = recipeListViewModel.uiState,
                    onItemClick = { domainRecipeModel ->
                        sharedViewModel.domainRecipeModel = domainRecipeModel
                        navHostController.navigate(
                            Navigation.BottomNavigation.SearchFeature.RecipeDetails
                        )


                    },
                    onSearch = {
                        recipeListViewModel.onEvent(
                            RecipeListViewModel.RecipeList.Event.SearchRecipe(
                                it
                            )
                        )
                    },
                    toggleFavorite = {
                        recipeListViewModel.toggleFavorite(it)
                    },
                    bottomNavBarSize = bottomNavBarSize,
                    favoriteItems = state
                )

            }
            composable<Navigation.BottomNavigation.SearchFeature.RecipeDetails> {

                RecipeDetailsScreen(sharedViewModel.domainRecipeModel) {
                    navHostController.popBackStack()
                }
            }
        }

    }

}