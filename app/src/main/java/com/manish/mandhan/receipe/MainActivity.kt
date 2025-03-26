package com.manish.mandhan.receipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.manish.mandhan.common.navigation.Navigation
import com.manish.mandhan.profile.presentation.navigation.ProfileFeatureNavigationApi
import com.manish.mandhan.presentation.navigation.SearchFeatureApi
import com.manish.mandhan.receipe.di.NavigationApiWrapper
import com.manish.mandhan.receipe.screens.bottom_navigation.MyBottomNavBar
import com.manish.mandhan.receipe.ui.theme.ReceipeTheme
import com.manish.mandhan.receipe.vm.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationApiWrapper: NavigationApiWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themeViewModel = viewModels<ThemeViewModel>()
        val current = mutableStateOf<String?>(null)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                themeViewModel.value.currentTheme.collect { currentTheme ->
                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.auto(
                            lightScrim = android.graphics.Color.TRANSPARENT,
                            darkScrim = android.graphics.Color.TRANSPARENT,
                        ) { currentTheme == "Dark" },
                        navigationBarStyle = SystemBarStyle.auto(
                            lightScrim = lightScrim,
                            darkScrim = darkScrim,
                        ) { currentTheme == "Dark" },
                    )
                    current.value = currentTheme
                }
            }
        }
        installSplashScreen().apply {
            setKeepOnScreenCondition { themeViewModel.value.shouldShowSplashScreen }
        }


        setContent {

            val navController = rememberNavController()
            val currentDestination = navController.currentBackStackEntryAsState().value?.destination

            val bottomNavBarSize = remember {
                mutableStateOf(0)
            }



            ReceipeTheme(themeViewModel = themeViewModel.value) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(), bottomBar = {
                        AnimatedVisibility(
                            visible = currentDestination?.route != Navigation.BottomNavigation.SearchFeature.RecipeDetails::class.qualifiedName,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            MyBottomNavBar(navController = navController) {
                                bottomNavBarSize.value = it
                            }
                        }
                    }) { innerPadding ->
                    AppNavigation(
                        navController = navController,
                        navigationApiWrapper = navigationApiWrapper,
                        modifier = Modifier.padding(innerPadding),
                        bottomNavBarSize = bottomNavBarSize.value,
                        toggleTheme = {
                            themeViewModel.value.changeTheme(
                                if (current.value == "Dark") "Light" else "Dark"
                            )
                        },
                        currentTheme = current
                    )

                }

            }
        }
    }
}

private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)
