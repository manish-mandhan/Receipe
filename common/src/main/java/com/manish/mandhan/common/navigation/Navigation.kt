package com.manish.mandhan.common.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class Navigation {


    @Serializable
    sealed class BottomNavigation : Navigation() {

        @Serializable
        sealed class SearchFeature : BottomNavigation() {

            @Serializable
            data object Root : SearchFeature()

            @Serializable
            data object RecipeList : SearchFeature()

            @Serializable
            data object RecipeDetails : SearchFeature()
        }


        @Serializable
        sealed class ProfileFeature : BottomNavigation() {

            @Serializable
            data object Root : ProfileFeature()

            @Serializable
            data object Profile : ProfileFeature()
        }


        @Serializable
        sealed class SettingFeature : BottomNavigation() {

            @Serializable
            data object Root : SettingFeature()

            @Serializable
            data object Settings : SettingFeature()
        }
    }


}