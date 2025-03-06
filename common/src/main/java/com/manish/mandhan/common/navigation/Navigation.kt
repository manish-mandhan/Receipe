package com.manish.mandhan.common.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class Navigation {


    @Serializable
    sealed class BottomNavigation(val route: String) : Navigation() {

        @Serializable
        sealed class SearchFeatureNavigation(private val string: String) : BottomNavigation(string) {

            @Serializable
            data object Root : SearchFeatureNavigation("search_feature_root")

            @Serializable
            data object RecipeList : SearchFeatureNavigation("search_feature_recipe_list")

            @Serializable
            data object RecipeDetails : SearchFeatureNavigation("search_feature_recipe_details")
        }


        @Serializable
        sealed class HomeNavigation(private val string: String) : BottomNavigation(string) {


            @Serializable
            data object Profile : HomeNavigation("home_profile")

            @Serializable
            data object Settings : HomeNavigation("home_settings")
        }
    }


}