package net.pop.foodify.ui.composables.fav

import net.pop.foodify.model.loacl.FavoriteMealEntity

sealed interface FavUiState {
    data object Loading : FavUiState
    data object Empty : FavUiState
    data class Success(val favorites: List<FavoriteMealEntity>) : FavUiState
    data class Error(val message: String) : FavUiState
}