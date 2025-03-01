package com.manish.mandhan.common.navigation

sealed class Navigation(val route: String) {

    sealed class SearchFeature(route: String) : Navigation(route) {
        data object Root : SearchFeature("/search_feature_graph")
        data object RecipeList : SearchFeature("/recipe_list")
        data object RecipeDetails : SearchFeature("/recipe_details/{id}") {
            fun sendId(id: String) = "/recipe_details/${id}"
        }
    }

}