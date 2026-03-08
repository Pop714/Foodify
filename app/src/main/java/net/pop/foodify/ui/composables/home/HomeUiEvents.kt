package net.pop.foodify.ui.composables.home

sealed interface HomeUiEvent {
    data class NavigateToDetails(val mealId: String) : HomeUiEvent
    data class ShowSnackbar(val message: String) : HomeUiEvent
}