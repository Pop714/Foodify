package net.pop.foodify.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.pop.foodify.model.loacl.CartEntity
import net.pop.foodify.repos.db.MealRepository
import net.pop.foodify.ui.composables.cart.CartUiState

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repo: MealRepository
): ViewModel() {
    val uiState: StateFlow<CartUiState> = repo.getAllCartItems()
        .map { cartItems ->
            if (cartItems.isEmpty()) {
                CartUiState.Empty
            } else {
                CartUiState.Success(cartItems)
            }
        }
        .catch { e ->
            emit(CartUiState.Error("Failed to load cart: ${e.message}"))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CartUiState.Loading
        )

    fun removeCartItem(item: CartEntity) {
        viewModelScope.launch {
            try {
                repo.deleteCartItem(item)
            } catch (e: Exception) {
                // Handle deletion error if necessary
            }
        }
    }
}