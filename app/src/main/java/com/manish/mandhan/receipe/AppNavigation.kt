package com.manish.mandhan.receipe

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manish.mandhan.common.navigation.Navigation
import com.manish.mandhan.presentation.navigation.SearchFeatureApi
import com.manish.mandhan.presentation.screens.shared.SharedViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    searchFeatureApi: SearchFeatureApi
) {

    val sharedViewModel: SharedViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Navigation.SearchFeature.Root) {

        searchFeatureApi.registerNavGraph(
            navHostController = navController,
            navGraphBuilder = this,
            sharedViewModel = sharedViewModel
        )

    }
}