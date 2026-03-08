package net.pop.foodify.ui.composables.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.pop.foodify.ui.composables.helpers.ErrorState
import net.pop.foodify.ui.composables.helpers.LoadingState

@Composable
fun DetailsMealItem(
    modifier: Modifier = Modifier,
    detailsUiState: DetailsUiState,
    isFavorite: Boolean,
    openSource: (String) -> Unit,
    shareRecipe: (String) -> Unit,
    openCartPopup: (List<Pair<String, String>>) -> Unit,
    toggleFav: (net.pop.foodify.model.remote.meals.Meal) -> Unit
) {
    when (detailsUiState) {
        is DetailsUiState.Loading -> LoadingState()
        is DetailsUiState.Error -> ErrorState(
            message = detailsUiState.message,
            onRetry = {}
        )
        is DetailsUiState.Success -> DetailsSuccessState(
            modifier = modifier,
            meal = detailsUiState.meal[0],
            isFavorite = isFavorite,
            openSource = openSource,
            shareRecipe = shareRecipe,
            openCartPopup = openCartPopup,
            toggleFav = toggleFav
        )
    }
}