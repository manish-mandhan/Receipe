package com.manish.mandhan.profile.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.manish.mandhan.common.navigation.Navigation
import com.manish.mandhan.profile.presentation.screens.favorite_recipes.FavoriteRecipesScreen
import com.manish.mandhan.profile.presentation.screens.favorite_recipes.FavoriteRecipesViewModel
import com.manish.mandhan.profile.presentation.screens.profile_root.ProfileScreen

interface ProfileFeatureNavigationApi {
    fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    )
}

class ProfileFeatureNavigationApiImpl : ProfileFeatureNavigationApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<Navigation.BottomNavigation.ProfileFeature.Root>(
            startDestination = Navigation.BottomNavigation.ProfileFeature.Profile
        ) {
            composable<Navigation.BottomNavigation.ProfileFeature.Profile> {
                ProfileScreen(
                    modifier = modifier,
                    onFavoriteCardClick = {
                        navHostController.navigate(Navigation.BottomNavigation.ProfileFeature.FavoriteRecipes) {
                            this.restoreState = true
                            launchSingleTop = true
                            popUpTo(Navigation.BottomNavigation.ProfileFeature.FavoriteRecipes) {
                                saveState = true
                            }
                        }
                    }
                )
            }

            composable<Navigation.BottomNavigation.ProfileFeature.FavoriteRecipes> {

                val parentEntry = remember(navHostController) {
                    navHostController.getBackStackEntry(Navigation.NavHost::class.qualifiedName!!)
                }

                val viewModel =
                    hiltViewModel<FavoriteRecipesViewModel>(viewModelStoreOwner = parentEntry)

                val uiState = viewModel.uiState.collectAsStateWithLifecycle()
                val uiEvent = viewModel.uiEvent

                FavoriteRecipesScreen(
                    uiState = uiState,
                    uiEvent = uiEvent,
                    toggleFavoriteRecipe = {
                        viewModel.toggleRecipe(it)
                    }
                )
            }
        }
    }
}