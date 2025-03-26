package com.manish.mandhan.search.data.submodule.favorite.mapper

import com.manish.mandhan.common.common_model.FavoriteModelEntity
import com.manish.mandhan.search.domain.model.FavoriteModel

fun FavoriteModel.toFavoriteModelEntity(): FavoriteModelEntity {
    return FavoriteModelEntity(
        id = id
    )
}

fun FavoriteModelEntity.domainModel(): FavoriteModel {
    return FavoriteModel(
        id = id
    )
}