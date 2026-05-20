package net.pop.foodify.presentation.screens.cart

sealed interface CartUiEvent {
    data class ShowToast(val message: String): CartUiEvent
}