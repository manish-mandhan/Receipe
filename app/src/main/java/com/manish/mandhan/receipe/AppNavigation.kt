package com.manish.mandhan.receipe

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.manish.mandhan.common.navigation.Navigation
import com.manish.mandhan.presentation.screens.shared.SharedViewModel
import com.manish.mandhan.receipe.di.NavigationApiWrapper

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigationApiWrapper: NavigationApiWrapper
) {

    val sharedViewModel: SharedViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Navigation.BottomNavigation.SearchFeature.Root
    ) {

        navigationApiWrapper.searchFeatureApi.registerNavGraph(
            navHostController = navController,
            navGraphBuilder = this,
            sharedViewModel = sharedViewModel
        )

        navigationApiWrapper.profileFeatureApi.registerGraph(
            navHostController = navController,
            navGraphBuilder = this,
            modifier = modifier
        )

        navigationApiWrapper.settingsFeatureApi.registerGraph(
            navHostController = navController,
            navGraphBuilder = this,
            modifier = modifier
        )
    }
}