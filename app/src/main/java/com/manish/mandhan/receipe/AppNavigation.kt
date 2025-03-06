package com.manish.mandhan.receipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.manish.mandhan.presentation.navigation.SearchFeatureApi
import com.manish.mandhan.presentation.screens.shared.SharedViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    searchFeatureApi: SearchFeatureApi
) {

    val sharedViewModel: SharedViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Navigation.BottomNavigation.SearchFeatureNavigation.Root
    ) {

        searchFeatureApi.registerNavGraph(
            navHostController = navController,
            navGraphBuilder = this,
            sharedViewModel = sharedViewModel
        )

        composable<Navigation.BottomNavigation.HomeNavigation.Profile> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Profile")
            }
        }

        composable<Navigation.BottomNavigation.HomeNavigation.Settings> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Settings")
            }
        }

    }
}