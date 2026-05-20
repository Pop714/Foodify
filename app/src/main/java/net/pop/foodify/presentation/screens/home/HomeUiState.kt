package net.pop.foodify.presentation.screens.home

import net.pop.foodify.data.remote.meals.MealsResponse

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(
        val filterList: List<Pair<String, String>>,
        val recipes: List<MealsResponse>,
        val filterType: String
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}