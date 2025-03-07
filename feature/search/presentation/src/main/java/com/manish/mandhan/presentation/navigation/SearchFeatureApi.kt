package com.manish.mandhan.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
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

        navGraphBuilder.navigation<Navigation.BottomNavigation.SearchFeature.Root>(
            startDestination = Navigation.BottomNavigation.SearchFeature.RecipeList
        ) {


            composable<Navigation.BottomNavigation.SearchFeature.RecipeList> {
                val recipeListViewModel: RecipeListViewModel = hiltViewModel()

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
                    }
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