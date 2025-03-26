package com.manish.mandhan.feature.settings

import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.manish.mandhan.common.navigation.Navigation
import com.manish.mandhan.feature.settings.screens.SettingsScreen


interface SettingsFeatureNavigationApi {
    fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier,
        toggleTheme: () -> Unit,
        currentTheme: State<String?>
    )
}

class SettingsFeatureNavigationApiImpl : SettingsFeatureNavigationApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier,
        toggleTheme: () -> Unit,
        currentTheme: State<String?>
    ) {
        navGraphBuilder.navigation<Navigation.BottomNavigation.SettingFeature.Root>(
            startDestination = Navigation.BottomNavigation.SettingFeature.Settings
        ) {
            composable<Navigation.BottomNavigation.SettingFeature.Settings> {
                SettingsScreen(
                    modifier = modifier,
                    toggleTheme = toggleTheme,
                    currentTheme = currentTheme
                )
            }
        }
    }
}