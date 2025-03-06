package com.manish.mandhan.receipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.manish.mandhan.presentation.navigation.SearchFeatureApi
import com.manish.mandhan.receipe.screens.bottom_navigation.MyBottomNavBar
import com.manish.mandhan.receipe.ui.theme.ReceipeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var searchGraphApi: SearchFeatureApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            ReceipeTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    MyBottomNavBar(navController = navController)
                }) { innerPadding ->
                    AppNavigation(navController = navController, searchFeatureApi = searchGraphApi)
                }
            }
        }
    }
}
