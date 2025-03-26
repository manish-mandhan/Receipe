package com.manish.mandhan.search.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manish.mandhan.search.data.model.OfflineRecipeDTO
import com.manish.mandhan.search.data.model.OfflineRecipeResponseModel

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe_dto_table WHERE `strMealId` LIKE '%' || :query || '%'")
    suspend fun getRecipesBySearch(query: String) : List<OfflineRecipeDTO>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: OfflineRecipeDTO)



}