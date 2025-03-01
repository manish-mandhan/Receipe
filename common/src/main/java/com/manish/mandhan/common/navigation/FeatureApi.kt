package com.manish.mandhan.common.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureApi {
    fun registerNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    )
}