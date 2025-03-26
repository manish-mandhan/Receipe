package com.manish.mandhan.profile.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.manish.mandhan.common.common_model.FavoriteModelEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteRecipeDao {

    @Query("SELECT * FROM favorite_recipes_table")
    fun getAllRecipeIds(): Flow<List<FavoriteModelEntity>>


    @Query("SELECT * FROM favorite_recipes_table WHERE id = :id")
    suspend fun isFavoriteRecipePresent(id: String): FavoriteModelEntity?


    @Insert
    suspend fun insertFavoriteRecipe(favoriteModelEntity: FavoriteModelEntity)

    @Query("DELETE FROM favorite_recipes_table WHERE id = :id")
    suspend fun deleteFavoriteRecipe(id: String)

}