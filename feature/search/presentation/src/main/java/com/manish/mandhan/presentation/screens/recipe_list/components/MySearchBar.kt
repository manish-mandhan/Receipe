package com.manish.mandhan.presentation.screens.recipe_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.manish.mandhan.presentation.screens.theme.onWhite


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    onSearch: (s: String) -> Unit,
) {
    val textState = rememberSaveable {
        mutableStateOf("")
    }
    SearchBar(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                ambientColor = MaterialTheme.colorScheme.secondary,
                spotColor = MaterialTheme.colorScheme.secondary
            )
            .background(MaterialTheme.colorScheme.primary)
            .padding(bottom = 16.dp),
        inputField = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {

                TextField(
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                        focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .clip(CircleShape)
                        .height(60.dp),
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    leadingIcon = {
                        IconButton(
                            onClick = {
                                onSearch(textState.value)
                            }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                        }

                    },
                )
            }
        },
        expanded = false,
        onExpandedChange = {
        }
    ) {

    }
}
