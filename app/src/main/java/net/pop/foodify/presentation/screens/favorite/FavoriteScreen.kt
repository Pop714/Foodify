package net.pop.foodify.presentation.screens.favorite

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.pop.foodify.presentation.screens.helpers.DetailsUiEvent
import net.pop.foodify.presentation.screens.helpers.ErrorState
import net.pop.foodify.presentation.screens.helpers.LoadingState

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onRecipeClicked: (String) -> Unit
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is DetailsUiEvent.NavigateToDetails -> {
                    onRecipeClicked(event.mealId)
                }
                is DetailsUiEvent.ShowSnackbar -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    when (uiState) {
        is FavoriteUiState.Loading -> LoadingState()
        is FavoriteUiState.Error -> ErrorState((uiState as FavoriteUiState.Error).message)
        is FavoriteUiState.Success -> SuccessState(
            (uiState as FavoriteUiState.Success).recipes,
            onMealClicked = viewModel::onRecipeClicked
        )
    }

}