package net.pop.foodify.ui.composables.home

import androidx.compose.runtime.Composable
import net.pop.foodify.ui.composables.helpers.ErrorState
import net.pop.foodify.ui.composables.helpers.LoadingState

@Composable
fun HomeScreenContent(
    loadData: () -> Unit,
    onMealClicked: (String) -> Unit,
    filterAccordingType: (String, String) -> Unit,
    uiState: HomeUiState
) {
    when (uiState) {
        is HomeUiState.Loading -> LoadingState()
        is HomeUiState.Error -> ErrorState(
            message = uiState.message,
            onRetry = { loadData() }
        )
        is HomeUiState.Success -> SuccessState(
            categories = uiState.categories,
            areas = uiState.countries,
            ingredients = uiState.ingredients,
            meals = uiState.mealItems,
            mealClicked = onMealClicked,
            filterTyped = filterAccordingType
        )
    }
}