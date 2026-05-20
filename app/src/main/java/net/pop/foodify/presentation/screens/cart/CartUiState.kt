package net.pop.foodify.presentation.screens.cart

import net.pop.foodify.data.local.entity.CartEntity

sealed interface CartUiState {
    object Loading: CartUiState
    data class Error(val message: String): CartUiState
    data class Success(val groupedItems: Map<CartHeaderModel, List<CartEntity>>): CartUiState
}