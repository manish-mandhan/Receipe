package com.manish.mandhan.common.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Navigation {

    @Serializable
    sealed class SearchFeature : Navigation() {

        @Serializable
        data object Root : SearchFeature()

        @Serializable
        data object RecipeList : SearchFeature()

        @Serializable
        data object RecipeDetails : SearchFeature()
    }

}