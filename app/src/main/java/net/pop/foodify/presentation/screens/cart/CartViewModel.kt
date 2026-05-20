package net.pop.foodify.presentation.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import net.pop.foodify.data.local.entity.CartEntity
import net.pop.foodify.data.repos.RecipeRepository

@HiltViewModel
class CartViewModel @Inject constructor(
    private val dbRepo: RecipeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CartUiState>(CartUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<CartUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadCartItems()
    }

    fun loadCartItems() {
        viewModelScope.launch {
            try {
                dbRepo.getAllCartItems().collect { allItems ->
                    val grouped = allItems.groupBy { item ->
                        CartHeaderModel(
                            mealName = item.mealName,
                            mealThumb = item.mealThumb
                        )
                    }
                    _uiState.value = CartUiState.Success(grouped)
                }
            } catch (e: Exception) {
                _uiState.value = CartUiState.Error("Failed to load cart items. ${e.message}")
            }
        }
    }

    fun removeCartItem(item: CartEntity) {
        viewModelScope.launch {
            dbRepo.deleteCartItem(item)
            _uiEvent.send(CartUiEvent.ShowToast("${item.ingredient} checked off!"))
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            dbRepo.clearCart()
            _uiEvent.send(CartUiEvent.ShowToast("Cart cleared"))
        }
    }

}