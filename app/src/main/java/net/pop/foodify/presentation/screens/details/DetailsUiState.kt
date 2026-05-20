package net.pop.foodify.presentation.screens.details

import net.pop.foodify.model.remote.meal.Meal

sealed interface DetailsUiState {
    object Loading: DetailsUiState
    data class Error(val message: String): DetailsUiState
    data class Success(val meal: List<Meal>): DetailsUiState
}