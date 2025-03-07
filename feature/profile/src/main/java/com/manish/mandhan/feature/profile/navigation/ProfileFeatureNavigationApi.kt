package com.manish.mandhan.feature.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.manish.mandhan.common.navigation.Navigation
import com.manish.mandhan.feature.profile.screens.ProfileScreen
import javax.inject.Inject

interface ProfileFeatureNavigationApi {
    fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    )
}

class ProfileFeatureNavigationApiImpl : ProfileFeatureNavigationApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<Navigation.BottomNavigation.ProfileFeature.Root>(
            startDestination = Navigation.BottomNavigation.ProfileFeature.Profile
        ) {
            composable<Navigation.BottomNavigation.ProfileFeature.Profile> {
                ProfileScreen()
            }
        }
    }
}