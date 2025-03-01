package com.manish.mandhan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
//import com.manish.mandhan.common.navigation.FeatureApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.manish.mandhan.common.navigation.Navigation
import com.manish.mandhan.presentation.screens.recipe_details.RecipeDetailsScreen
import com.manish.mandhan.presentation.screens.recipe_list.RecipeListScreen
import com.manish.mandhan.presentation.screens.recipe_list.RecipeListViewModel
import com.manish.mandhan.presentation.screens.shared.SharedViewModel
import javax.inject.Inject

interface SearchFeatureApi // : NavigationApi
{
    fun registerNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController,
        sharedViewModel: SharedViewModel
    )
}


class SearchFeatureApiImpl : SearchFeatureApi {

    override fun registerNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController,
        sharedViewModel: SharedViewModel
    ) {

        navGraphBuilder.navigation<Navigation.SearchFeature.Root>(
            startDestination = Navigation.SearchFeature.RecipeList
        ) {


            composable<Navigation.SearchFeature.RecipeList> {
                val recipeListViewModel: RecipeListViewModel = hiltViewModel()

                RecipeListScreen(
                    uiState = recipeListViewModel.uiState,
                    onItemClick = { domainRecipeModel ->
                        sharedViewModel.domainRecipeModel = domainRecipeModel

                        navHostController.navigate(
                            Navigation.SearchFeature.RecipeDetails
                        )
                    },
                    onSearch = {
                        recipeListViewModel.onEvent(
                            RecipeListViewModel.RecipeList.Event.SearchRecipe(
                                it
                            )
                        )
                    }
                )

            }
            composable<Navigation.SearchFeature.RecipeDetails> {

                RecipeDetailsScreen(sharedViewModel.domainRecipeModel)
            }
        }

    }

}