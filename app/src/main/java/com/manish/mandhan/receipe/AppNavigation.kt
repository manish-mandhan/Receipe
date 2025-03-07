package com.manish.mandhan.receipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manish.mandhan.common.navigation.Navigation
import com.manish.mandhan.feature.profile.navigation.ProfileFeatureNavigationApi
import com.manish.mandhan.presentation.navigation.SearchFeatureApi
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
            navGraphBuilder = this
        )

        navigationApiWrapper.settingsFeatureApi.registerGraph(
            navHostController = navController,
            navGraphBuilder = this
        )
    }
}