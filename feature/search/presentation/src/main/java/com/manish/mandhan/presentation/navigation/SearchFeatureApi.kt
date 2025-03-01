package com.manish.mandhan.presentation.navigation

import com.manish.mandhan.common.navigation.FeatureApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.manish.mandhan.common.navigation.Navigation

interface SearchFeatureApi : FeatureApi {

}


class SearchFeatureApiImpl : SearchFeatureApi {
    override fun registerNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        navGraphBuilder.navigation(
            route = Navigation.SearchFeature.Root.route,
            startDestination = Navigation.SearchFeature.RecipeList.route
        ) {
            composable(route = Navigation.SearchFeature.RecipeList.route) {

            }
            composable(route = Navigation.SearchFeature.RecipeDetails.route) {

            }
        }

    }

}