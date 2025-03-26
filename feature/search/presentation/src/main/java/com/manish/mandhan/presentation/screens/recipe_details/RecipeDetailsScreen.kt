package com.manish.mandhan.presentation.screens.recipe_details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.manish.mandhan.presentation.R
import com.manish.mandhan.search.domain.model.DomainRecipeModel
import kotlinx.coroutines.flow.StateFlow


val TAG = "DEBUGGING LOG"

@Composable
fun RecipeDetailsScreen(domainRecipeModel: DomainRecipeModel?, onBackPress: () -> Unit) {


    val imagePainter: Painter = rememberAsyncImagePainter(model = domainRecipeModel?.strMealThumb)
    Scaffold { _ ->

        val screenHeight = LocalConfiguration.current.screenHeightDp
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .fillMaxSize()
                .navigationBarsPadding()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.4f.dp * screenHeight)
            ) {
                IconButton(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .padding(bottom = 32.dp)
                        .zIndex(1f),
                    onClick = onBackPress
                ) {
                    Icon(
                        tint = Color.White,
                        modifier = Modifier
                            .size(30.dp),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }

                AsyncImage(
                    modifier = Modifier
                        .matchParentSize(),
                    onError = {
                        Log.e(
                            TAG,
                            "RecipeDetailsScreen: Error : ${it.result.throwable.message}"
                        )
                    },
                    alpha = 0.65f,
                    colorFilter = ColorFilter.tint(
                        color = Color.Black,
                        blendMode = BlendMode.Screen
                    ),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(domainRecipeModel?.strMealThumb)  // Image URL
                        .crossfade(true)                        // Enable smooth transition
                        .placeholder(R.drawable.place_holder_image)    // Placeholder while loading
                        .error(R.drawable.place_holder_image)                // Error image if loading fails
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                )


                Text(
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    lineHeight = 55.sp,
                    fontSize = 55.sp,
                    text = domainRecipeModel?.strMeal ?: "",
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 90.dp)
                        .align(
                            Alignment.BottomStart
                        )
                        .padding(
                            start = 16.dp, bottom = 8.dp
                        )
                )
            }
            Text(
                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                text = "CATEGORY | ${domainRecipeModel?.strCategory?.uppercase()}",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
                fontSize = 14.sp
            )
            Text(
                text = domainRecipeModel?.strInstruction ?: "",
                modifier = Modifier.padding(horizontal = 18.dp)
            )
        }
    }


}
//
//@Composable
//fun DetailScreenPrev() {
//    val chickenHandiRecipe = DomainRecipeModel(
//        strInstruction = "Take a large pot or wok, big enough to cook all the chicken, and heat the oil in it. Once the oil is hot, add sliced onion and fry them until deep golden brown. Then take them out on a plate and set aside.\r\nTo the same pot, add the chopped garlic and sauté for a minute. Then add the chopped tomatoes and cook until tomatoes turn soft. This would take about 5 minutes.\r\nThen return the fried onion to the pot and stir. Add ginger paste and sauté well.\r\nNow add the cumin seeds, half of the coriander seeds and chopped green chillies. Give them a quick stir.\r\nNext goes in the spices – turmeric powder and red chilli powder. Sauté the spices well for couple of minutes.\r\nAdd the chicken pieces to the wok, season it with salt to taste and cook the chicken covered on medium-low heat until the chicken is almost cooked through. This would take about 15 minutes. Slowly sautéing the chicken will enhance the flavor, so do not expedite this step by putting it on high heat.\r\nWhen the oil separates from the spices, add the beaten yogurt keeping the heat on lowest so that the yogurt doesn’t split. Sprinkle the remaining coriander seeds and add half of the dried fenugreek leaves. Mix well.\r\nFinally add the cream and give a final mix to combine everything well.\r\nSprinkle the remaining kasuri methi and garam masala and serve the chicken handi hot with naan or rotis. Enjoy!",
//        strMealThumb = "https://www.themealdb.com/images/media/meals/wyxwsp1486979827.jpg",
//        strMeal = "Chicken Handi",
//        strImageSource = "",
//        dateModified = "",
//        idMeal = "52795",
//        strArea = "Indian",
//        strCategory = "Chicken",
//        strSource = "",
//        strTags = "",
//        strYoutube = "https://www.youtube.com/watch?v=IO0issT0Rmc",
//        strIngredients = listOf(
//            "Chicken" to "1.2 kg",
//            "Onion" to "5 thinly sliced",
//            "Tomatoes" to "2 finely chopped",
//            "Garlic" to "8 cloves chopped",
//            "Ginger paste" to "1 tbsp",
//            "Vegetable oil" to "¼ cup",
//            "Cumin seeds" to "2 tsp",
//            "Coriander seeds" to "3 tsp",
//            "Turmeric powder" to "1 tsp",
//            "Chilli powder" to "1 tsp",
//            "Green chilli" to "2",
//            "Yogurt" to "1 cup",
//            "Cream" to "¾ cup",
//            "Fenugreek" to "3 tsp Dried",
//            "Garam masala" to "1 tsp",
//            "Salt" to "To taste"
//        )
//    )
//
//    RecipeDetailsScreen(domainRecipeModel = chickenHandiRecipe)
//
//
//}