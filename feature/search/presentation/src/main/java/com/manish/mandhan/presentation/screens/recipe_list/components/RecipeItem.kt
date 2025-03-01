package com.manish.mandhan.presentation.screens.recipe_list.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.manish.mandhan.presentation.screens.theme.onWhite
import com.manish.mandhan.search.domain.model.DomainRecipeModel
import kotlin.math.roundToInt

@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,
    data: DomainRecipeModel,
    onItemClick: (domainRecipeModel: DomainRecipeModel) -> Unit
) {
    val offsetX = remember {
        mutableStateOf(0f)
    }
    val offsetXAnim = animateFloatAsState(
        targetValue = offsetX.value,
        label = "",
        animationSpec = tween(100)
    )




    Box(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectHorizontalDragGestures(onDragEnd = {
                    offsetX.value = 0f
                }) { _, dragAmount ->
                    offsetX.value += dragAmount
                    if (offsetX.value < 0f) {
                        offsetX.value = 0f
                    }

//                    offsetX.value.coerceIn(0f,600f)

                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .matchParentSize()
                .background(onWhite)
        ) {

        }
        Row(
            modifier = Modifier
                .offset {
                    IntOffset(offsetXAnim.value.roundToInt(), 0)
                }
                .fillMaxWidth()
                .background(Color.White)
                .clickable {
                    if (offsetX.value == 0f) {

                        onItemClick(data)
                    }
                },
        ) {

            AsyncImage(
                modifier = Modifier.size(110.dp),
                model = data.strMealThumb,
                contentDescription = null,
                contentScale = ContentScale.FillWidth

            )
            Column(modifier = Modifier.fillMaxWidth(.70f)) {

                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = data.strMeal,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .padding(start = 12.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .padding(horizontal = 12.dp),
                    text = data.strInstruction,
                    maxLines = 2,
                    fontSize = 14.sp,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis
                )

            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = data.strCategory,
                modifier = Modifier.padding(end = 14.dp, top = 14.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
    Spacer(
        modifier = Modifier
            .height(16.dp)
            .background(onWhite)
    )
}
