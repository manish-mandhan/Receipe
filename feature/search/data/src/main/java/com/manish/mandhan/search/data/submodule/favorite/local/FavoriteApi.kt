package com.manish.mandhan.search.data.submodule.favorite.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manish.mandhan.common.common_model.FavoriteModelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteApi {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteModelEntity)


    @Query("SELECT * FROM favorite_recipes_table WHERE id = :id")
    suspend fun isRecipePresent(id: String): List<FavoriteModelEntity>

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteModelEntity)


    @Query("SELECT * FROM favorite_recipes_table WHERE id IN (:ids)")
    fun getListOfItemsByListOfIds(ids: List<String>): Flow<List<FavoriteModelEntity>>

}