package com.manish.mandhan.receipe.screens.bottom_navigation

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.manish.mandhan.common.navigation.Navigation

@Composable
fun MyBottomNavBar(modifier: Modifier = Modifier, navController: NavHostController) {
//    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val items = listOf(
        BottomNavItem(
            bottomNavScreen = Navigation.BottomNavigation.SearchFeature.Root,
            icon = Icons.Default.Search,
            label = "Search"
        ),
        BottomNavItem(
            bottomNavScreen = Navigation.BottomNavigation.ProfileFeature.Root,
            icon = Icons.Default.Person,
            label = "Profile"
        ),
        BottomNavItem(
            bottomNavScreen = Navigation.BottomNavigation.SettingFeature.Root,
            icon = Icons.Default.Settings,
            label = "Settings"
        )
    )
    BottomAppBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = false,
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

data class BottomNavItem(
    val bottomNavScreen: Navigation.BottomNavigation,
    val icon: ImageVector,
    val label: String
)
