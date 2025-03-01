package com.manish.mandhan.receipe

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manish.mandhan.common.navigation.Navigation
import com.manish.mandhan.presentation.navigation.SearchFeatureApi

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    searchFeatureApi: SearchFeatureApi
) {
    NavHost(navController = navController, startDestination = Navigation.SearchFeature.Root.route) {

        searchFeatureApi.registerNavGraph(navHostController = navController, navGraphBuilder = this)

    }
}