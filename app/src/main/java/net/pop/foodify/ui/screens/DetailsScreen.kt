package net.pop.foodify.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.pop.foodify.ui.composables.details.CartPopupDialog
import net.pop.foodify.ui.composables.details.DetailsMealItem
import net.pop.foodify.ui.composables.details.DetailsUiEvent
import net.pop.foodify.viewmodels.DetailsViewModel

@Composable
fun DetailsScreen(
    mealId: String,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(key1 = mealId) {
        viewModel.getMealById(mealId)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isFavorite by viewModel.isFavorite.collectAsStateWithLifecycle()

    val showCartPopup by viewModel.showCartPopup.collectAsStateWithLifecycle()
    val cartIngredients by viewModel.cartIngredients.collectAsStateWithLifecycle()

    DetailsMealItem(
        detailsUiState = uiState,
        isFavorite = isFavorite,
        openSource = { url -> viewModel.openSourceUrl(url) },
        shareRecipe = { viewModel.shareRecipe(mealId) },
        openCartPopup = { ingredients -> viewModel.openCartPopup(ingredients) },
        toggleFav = { meal -> viewModel.toggleFavorite(meal) }
    )

    if (showCartPopup) {
        CartPopupDialog(
            ingredients = cartIngredients,
            onDismiss = { viewModel.closeCartPopup() },
            onAddToCart = { ingredient, measure ->
                viewModel.addToCart(ingredient, measure)
            }
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is DetailsUiEvent.OpenWebBrowser -> {
                    uriHandler.openUri(event.url)
                }
                is DetailsUiEvent.ShareRecipe -> {
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "Check out this recipe! ${event.url}")
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, "Share Recipe")
                    context.startActivity(shareIntent)
                }
                is DetailsUiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}