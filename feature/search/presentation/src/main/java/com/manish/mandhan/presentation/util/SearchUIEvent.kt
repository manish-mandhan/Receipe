package com.manish.mandhan.presentation.util

sealed class RecipeUIEvent {
    data class onSearch(val query: String) : RecipeUIEvent()
    data class onClickRecipeItem(val id: String) : RecipeUIEvent()
}