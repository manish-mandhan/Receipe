package com.manish.mandhan.profile.presentation.screens.favorite_recipes.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manish.mandhan.profile.domain.model.FavoriteRecipeModel
import com.manish.mandhan.common.R
import kotlin.math.roundToInt
import coil.compose.AsyncImage

@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,
    data: FavoriteRecipeModel,
    onItemClick: (item: FavoriteRecipeModel) -> Unit,
    isFavorite: Boolean,
    toggleFavorite: (String) -> Unit
) {



    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .matchParentSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onPrimary)
                .clickable {
                    onItemClick(data)
                },
        ) {

            AsyncImage(
                modifier = Modifier.size(100.dp),
                model = data.strMealThumb,
                contentDescription = null,
                contentScale = ContentScale.FillWidth

            )
            Column(modifier = Modifier.fillMaxWidth(.70f)) {

                Text(
                    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = data.strMeal,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .padding(start = 12.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Text(
                    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .padding(horizontal = 12.dp),
                    text = data.strInstruction,
                    maxLines = 2,
                    fontSize = 12.sp,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis
                )

            }
            Spacer(modifier = Modifier.weight(1f))
            Column {
                Text(
                    fontFamily = FontFamily(Font(R.font.montserrat_extra_bold)),
                    text = data.strCategory,
                    modifier = Modifier.padding(end = 14.dp, top = 14.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    toggleFavorite(data.idMeal)
                }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null
                    )
                }
            }
        }
    }
    Spacer(
        modifier = Modifier
            .height(16.dp)
            .background(MaterialTheme.colorScheme.primary)
    )
}
