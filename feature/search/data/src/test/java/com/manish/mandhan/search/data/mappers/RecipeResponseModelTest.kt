package com.manish.mandhan.search.data.mappers

import com.manish.mandhan.search.data.model.RecipeDTO
import com.manish.mandhan.search.data.model.RecipeResponseModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class RecipeResponseModelTest {
    @Test
    fun testToDomainModel() {
        val response = RecipeResponseModel(
            meals = listOf(
                RecipeDTO(
                    null, null, null, null, null,
                    null, null, null, null, null, null, null,
                    null, null, null, null,
                    null, null, null, null, null, null,
                    null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null,
                    null, null, null, null, null,
                    null, null, null,
                    "",
                    "",
                    ""
                ),
                RecipeDTO(
                    null, null, null, null, null,
                    null, null, null, null, null, null, null,
                    null, null, null, null,
                    null, null, null, null, null, null,
                    null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null,
                    null, null, null, null, null,
                    null, null, null,
                    "",
                    "",
                    ""
                ),
                RecipeDTO(
                    null, null, null, null, null,
                    null, null, null, null, null, null, null,
                    null, null, null, null,
                    null, null, null, null, null, null,
                    null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null,
                    null, null, null, null, null,
                    null, null, null,
                    "",
                    "",
                    ""
                )
            )
        )

        var iter = 0
        response.meals?.mapIndexed { index, value ->

            iter++
        } ?: { iter = -1 }
       assertEquals(1,iter)


    }
}