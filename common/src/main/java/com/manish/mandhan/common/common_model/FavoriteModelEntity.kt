package com.manish.mandhan.common.common_model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_recipes_table")
class FavoriteModelEntity(
    @PrimaryKey
    override val id: String,
) : FavoriteModelInterface