package com.manish.mandhan.receipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.manish.mandhan.common.navigation.Navigation
import com.manish.mandhan.feature.profile.navigation.ProfileFeatureNavigationApi
import com.manish.mandhan.presentation.navigation.SearchFeatureApi
import com.manish.mandhan.receipe.di.NavigationApiWrapper
import com.manish.mandhan.receipe.screens.bottom_navigation.MyBottomNavBar
import com.manish.mandhan.receipe.ui.theme.ReceipeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationApiWrapper: NavigationApiWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentDestination = navController.currentBackStackEntryAsState().value?.destination
            ReceipeTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    AnimatedVisibility(
                        visible = currentDestination?.route != Navigation.BottomNavigation.SearchFeature.RecipeDetails::class.qualifiedName,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        MyBottomNavBar(navController = navController)
                    }


                }) { innerPadding ->
                    AppNavigation(
                        navController = navController,
                        navigationApiWrapper = navigationApiWrapper,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
