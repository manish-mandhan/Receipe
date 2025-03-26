package com.manish.mandhan.presentation.screens.recipe_list.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.manish.mandhan.presentation.R

val TAG = "DEBUGGING LOG"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    onSearch: (s: String) -> Unit,
    modifier: Modifier = Modifier,
    gap: @Composable ColumnScope.() -> Unit
) {

    // scroll base
    val textState = rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = modifier

    ) {


        Text(
            fontSize = 24.sp,
            text = "Search Recipes",
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .padding(horizontal = 12.dp)
        )
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            fontSize = 14.sp,
            text = "Browse your favorite recipes",
            fontFamily = FontFamily(Font(R.font.montserrat_medium))
        )
        gap()

        SearchBar (
            windowInsets = WindowInsets(0.dp),
            modifier = Modifier
                .padding(top = 24.dp)
                .background(MaterialTheme.colorScheme.primary),
            inputField = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
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
                            .height(50.dp),
                        value = textState.value,
                        onValueChange = {
                            textState.value = it
                            onSearch(textState.value)


                        },
                        leadingIcon = {
                            IconButton(
                                onClick = {
                                    onSearch(textState.value)
                                }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search"
                                )
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

}
