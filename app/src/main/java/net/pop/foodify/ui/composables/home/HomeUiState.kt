package net.pop.foodify.ui.composables.home

import net.pop.foodify.model.remote.areas.Area
import net.pop.foodify.model.remote.categories.Category
import net.pop.foodify.model.remote.ingredients.Ingredient
import net.pop.foodify.model.remote.meals.MealsResponse

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(
        val countries: List<Area>,
        val categories: List<Category>,
        val ingredients: List<Ingredient>,
        val mealItems: List<MealsResponse>
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}