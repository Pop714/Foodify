package net.pop.foodify.presentation.screens.details

sealed interface DetailsUiEvent {
    data class ShowToast(val message: String): DetailsUiEvent
    data class OpenRecipeWeb(val url: String): DetailsUiEvent
    data class ShareRecipe(val url: String): DetailsUiEvent
}