package net.pop.foodify.ui.composables.details

import net.pop.foodify.model.remote.meal.Meal

sealed interface DetailsUiState {
    object Loading : DetailsUiState
    data class Success(
        val meal: List<Meal>
    ) : DetailsUiState
    data class Error(val message: String) : DetailsUiState
}