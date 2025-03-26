package com.manish.mandhan.search.data.repository

import com.manish.mandhan.common.resource.Result
import com.manish.mandhan.search.data.local.RecipeDao
import com.manish.mandhan.search.data.mappers.toDomainModel
import com.manish.mandhan.search.data.model.RecipeDTO
import com.manish.mandhan.search.data.model.RecipeResponseModel
import com.manish.mandhan.search.data.remote.SearchRecipeApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.net.UnknownHostException


class SearchRecipeRepositoryImplTest {


    private val searchRecipeApi: SearchRecipeApi = mock(SearchRecipeApi::class.java)
    private val dao: RecipeDao = mock(RecipeDao::class.java)

    fun testRepoImpl() = runTest {

    }

//    @Test
//    fun test_repo() = runTest {
//        `when`(searchRecipeApi.getProductByName("chicken"))
//            .thenReturn(RecipeResponseModel(listOf(mapToRecipeDTO())))
//
//        val test = SearchRecipeRepositoryImpl(searchRecipeApi, dao)
//
//        val response = test.getRecipeByName("chicken")
//
//        assertEquals(listOf(mapToRecipeDTO()).toDomainModel(), response.data)
//    }
//
//    @Test
//    fun test_nullable() = runTest {
//        `when`(searchRecipeApi.getProductByName("chicken"))
//            .thenReturn(RecipeResponseModel())
//
//
//        val test = SearchRecipeRepositoryImpl(searchRecipeApi, dao)
//
//        val response = test.getRecipeByName("chicken")
//
//        assertEquals("No Result Found", response.message)
//    }
//
//    @Test
//    fun test_backend_response_fails() = runTest {
//
//        doThrow(UnknownHostException()).`when`(searchRecipeApi).getProductByName("chicken")
//
//
//        val test = SearchRecipeRepositoryImpl(searchRecipeApi, dao)
//
//        val response = test.getRecipeByName("chicken")
//
//        assertEquals("No Internet", response.message)
//    }
}

fun mapToRecipeDTO(): RecipeDTO {
    return RecipeDTO(
        dateModified = null,
        idMeal = "52795",
        strArea = "Indian",
        strCategory = "Chicken",
        strCreativeCommonsConfirmed = null,
        strDrinkAlternate = null,
        strImageSource = null,
        strIngredient1 = "Chicken",
        strIngredient10 = "Chilli powder",
        strIngredient11 = "Green chilli",
        strIngredient12 = "Yogurt",
        strIngredient13 = "Cream",
        strIngredient14 = "fenugreek",
        strIngredient15 = "Garam masala",
        strIngredient16 = "Salt",
        strIngredient17 = "",
        strIngredient18 = "",
        strIngredient19 = "",
        strIngredient2 = "Onion",
        strIngredient20 = "",
        strIngredient3 = "Tomatoes",
        strIngredient4 = "Garlic",
        strIngredient5 = "Ginger paste",
        strIngredient6 = "Vegetable oil",
        strIngredient7 = "Cumin seeds",
        strIngredient8 = "Coriander seeds",
        strIngredient9 = "Turmeric powder",
        strInstructions = """
            Take a large pot or wok, big enough to cook all the chicken, and heat the oil in it. 
            Once the oil is hot, add sliced onion and fry them until deep golden brown. 
            Then take them out on a plate and set aside.
            To the same pot, add the chopped garlic and sauté for a minute. Then add the chopped tomatoes 
            and cook until tomatoes turn soft. This would take about 5 minutes.
            Then return the fried onion to the pot and stir. Add ginger paste and sauté well.
            Now add the cumin seeds, half of the coriander seeds and chopped green chillies. Give them a quick stir.
            Next goes in the spices – turmeric powder and red chilli powder. Sauté the spices well for couple of minutes.
            Add the chicken pieces to the wok, season it with salt to taste and cook the chicken covered on medium-low heat 
            until the chicken is almost cooked through. This would take about 15 minutes. Slowly sautéing the chicken will 
            enhance the flavor, so do not expedite this step by putting it on high heat.
            When the oil separates from the spices, add the beaten yogurt keeping the heat on lowest so that the yogurt 
            doesn’t split. Sprinkle the remaining coriander seeds and add half of the dried fenugreek leaves. Mix well.
            Finally add the cream and give a final mix to combine everything well.
            Sprinkle the remaining kasuri methi and garam masala and serve the chicken handi hot with naan or rotis. Enjoy!
        """.trimIndent(),
        strMeal = "Chicken Handi",
        strMealThumb = "https://www.themealdb.com/images/media/meals/wyxwsp1486979827.jpg",
        strMeasure1 = "1.2 kg",
        strMeasure10 = "1 tsp",
        strMeasure11 = "2",
        strMeasure12 = "1 cup",
        strMeasure13 = "¾ cup",
        strMeasure14 = "3 tsp Dried",
        strMeasure15 = "1 tsp",
        strMeasure16 = "To taste",
        strMeasure17 = "",
        strMeasure18 = "",
        strMeasure19 = "",
        strMeasure2 = "5 thinly sliced",
        strMeasure20 = "",
        strMeasure3 = "2 finely chopped",
        strMeasure4 = "8 cloves chopped",
        strMeasure5 = "1 tbsp",
        strMeasure6 = "¼ cup",
        strMeasure7 = "2 tsp",
        strMeasure8 = "3 tsp",
        strMeasure9 = "1 tsp",
        strSource = "",
        strTags = null,
        strYoutube = "https://www.youtube.com/watch?v=IO0issT0Rmc"
    )
}
