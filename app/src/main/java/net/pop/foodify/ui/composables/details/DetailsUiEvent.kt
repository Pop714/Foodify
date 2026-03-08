package net.pop.foodify.ui.composables.details

sealed interface DetailsUiEvent {
    data class OpenWebBrowser(val url: String) : DetailsUiEvent
    data class ShareRecipe(val url: String) : DetailsUiEvent
    data class ShowToast(val message: String) : DetailsUiEvent
}