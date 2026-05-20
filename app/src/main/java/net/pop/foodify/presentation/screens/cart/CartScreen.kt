package net.pop.foodify.presentation.screens.cart

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.pop.foodify.presentation.screens.details.LoadingState
import net.pop.foodify.presentation.screens.helpers.ErrorState

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is CartUiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    when (uiState) {
        is CartUiState.Loading -> LoadingState()
        is CartUiState.Error -> ErrorState((uiState as CartUiState.Error).message)
        is CartUiState.Success -> {
            val groupedItems = (uiState as CartUiState.Success).groupedItems
            SuccessState(
                groupedCartItems = groupedItems,
                onRemoveItem = viewModel::removeCartItem,
                onClearCart = viewModel::clearCart
            )
        }
    }

}