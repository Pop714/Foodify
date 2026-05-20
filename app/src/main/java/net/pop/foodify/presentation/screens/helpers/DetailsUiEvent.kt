package net.pop.foodify.presentation.screens.helpers

sealed interface DetailsUiEvent {
    data class NavigateToDetails(val mealId: String) : DetailsUiEvent
    data class ShowSnackbar(val message: String) : DetailsUiEvent
}