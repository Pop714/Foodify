package net.pop.foodify.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.pop.foodify.ui.composables.cart.CartScreenContent
import net.pop.foodify.viewmodels.CartViewModel

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CartScreenContent(
        uiState,
        removeCartItem = viewModel::removeCartItem
    )
}