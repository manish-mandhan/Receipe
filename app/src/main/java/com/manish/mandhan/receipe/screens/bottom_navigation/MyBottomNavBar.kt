package com.manish.mandhan.receipe.screens.bottom_navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.zIndex
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.manish.mandhan.common.navigation.Navigation
import kotlin.math.log

@Composable
fun MyBottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    bottomBarSize: (Int) -> Unit
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    val items = listOf(
        BottomNavItem(
            bottomNavScreen = Navigation.BottomNavigation.SearchFeature.RecipeList,
            icon = Icons.Default.Search,
            label = "Search"
        ),
        BottomNavItem(
            bottomNavScreen = Navigation.BottomNavigation.ProfileFeature.Profile,
            icon = Icons.Default.Person,
            label = "Profile"
        ),
        BottomNavItem(
            bottomNavScreen = Navigation.BottomNavigation.SettingFeature.Settings,
            icon = Icons.Default.Settings,
            label = "Settings"
        )
    )
    val TAG = "SCROLL LOG"
    Box(modifier = Modifier
        .background(Color.Red)
        .onSizeChanged {
            Log.d(TAG, "MyBottomNavBar: ${it.height}")
            bottomBarSize(it.height)
        }) {
        BottomAppBar(
            modifier = Modifier
                .background(Color.Transparent)
                .zIndex(10f),
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = Color.DarkGray
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.primary),
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) },
                    selected = currentDestination?.route == item.bottomNavScreen::class.qualifiedName,
                    onClick = {
                        navController.navigate(item.bottomNavScreen) {
                            popUpTo(Navigation.BottomNavigation.SearchFeature.RecipeList) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }

                    }
                )

            }

        }

    }
}

data class BottomNavItem(
    val bottomNavScreen: Navigation.BottomNavigation,
    val icon: ImageVector,
    val label: String
)
