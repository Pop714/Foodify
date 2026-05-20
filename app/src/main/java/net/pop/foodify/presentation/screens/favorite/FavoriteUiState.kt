package net.pop.foodify.presentation.screens.favorite

import net.pop.foodify.data.local.entity.FavoriteRecipeEntity
import net.pop.foodify.data.remote.meals.MealsResponse

sealed interface FavoriteUiState {
    object Loading : FavoriteUiState
    data class Error(val message: String) : FavoriteUiState
    data class Success(
        val recipes: List<FavoriteRecipeEntity>
    ) : FavoriteUiState
}