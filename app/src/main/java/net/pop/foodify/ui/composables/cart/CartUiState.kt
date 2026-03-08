package net.pop.foodify.ui.composables.cart

import net.pop.foodify.model.loacl.CartEntity

sealed interface CartUiState {
    data object Loading : CartUiState
    data object Empty : CartUiState
    data class Success(val items: List<CartEntity>) : CartUiState
    data class Error(val message: String) : CartUiState
}