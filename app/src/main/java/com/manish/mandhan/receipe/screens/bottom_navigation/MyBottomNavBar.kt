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
import androidx.navigation.NavController
import androidx.compose.material3.Icon
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.manish.mandhan.common.navigation.Navigation

@Composable
fun MyBottomNavBar(modifier: Modifier = Modifier, navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route


    val items = listOf(
        BottomNavItem(
            Navigation.BottomNavigation.SearchFeatureNavigation.RecipeList,
            Icons.Default.Search,
            "Search"
        ),
        BottomNavItem(
            Navigation.BottomNavigation.HomeNavigation.Profile,
            Icons.Default.Person,
            "Profile"
        ),
        BottomNavItem(
            Navigation.BottomNavigation.HomeNavigation.Settings,
            Icons.Default.Settings,
            "Settings"
        )
    )
    BottomAppBar {

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.bottomNavScreen.route,
                onClick = {
                    if (currentRoute != item.bottomNavScreen.route) {
                        navController.navigate(item.bottomNavScreen) {
                            popUpTo(Navigation.BottomNavigation.SearchFeatureNavigation.RecipeList) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
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
