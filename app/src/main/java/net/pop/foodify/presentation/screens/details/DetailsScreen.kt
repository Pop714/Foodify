package net.pop.foodify.presentation.screens.details

import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.pop.foodify.presentation.screens.helpers.ErrorState

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    onBackClicked: () -> Unit,
    id: String
) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(key1 = id) {
        viewModel.getMealDetailsById(id)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isFavorite by viewModel.isFav.collectAsStateWithLifecycle()

    when (uiState) {
        is DetailsUiState.Loading -> LoadingState()
        is DetailsUiState.Error -> ErrorState((uiState as DetailsUiState.Error).message)
        is DetailsUiState.Success -> SuccessState(
            meal = (uiState as DetailsUiState.Success).meal[0],
            isFav = isFavorite,
            toggleFav = viewModel::toggleFavorite,
            onBackClick = onBackClicked,
            shareRecipe = viewModel::shareRecipe,
            openWeb = viewModel::openSourceUrl,
            addToCart = viewModel::addRecipeItemsToCart
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is DetailsUiEvent.OpenRecipeWeb -> {
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