package net.pop.foodify.ui.composables.fav

import androidx.compose.runtime.Composable
import net.pop.foodify.ui.composables.helpers.ErrorState
import net.pop.foodify.ui.composables.helpers.LoadingState

@Composable
fun FavScreenContent(
    onMealClicked: (String) -> Unit,
    uiState: FavUiState
) {
    when (uiState) {
        is FavUiState.Loading -> LoadingState()
        is FavUiState.Error -> ErrorState(
            message = uiState.message,
            onRetry = {  }
        )

        is FavUiState.Success -> FavSuccessState(
            uiState.favorites,
            mealClicked = onMealClicked
        )

        is FavUiState.Empty -> FavEmptyState()
    }
}