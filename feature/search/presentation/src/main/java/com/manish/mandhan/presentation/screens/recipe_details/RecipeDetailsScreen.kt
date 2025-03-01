package com.manish.mandhan.presentation.screens.recipe_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.manish.mandhan.search.domain.model.DomainRecipeModel
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailsScreen(domainRecipeModel: DomainRecipeModel?) {


    Scaffold(
        topBar = {
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


            MediumTopAppBar(
                title = { Text("Details Screen", style = MaterialTheme.typography.headlineMedium) },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        )
        {
            AsyncImage(
                modifier = Modifier.height(330.dp),
                model = domainRecipeModel?.strMealThumb,
                contentDescription = "", contentScale = ContentScale.FillBounds
            )
        }
    }


}