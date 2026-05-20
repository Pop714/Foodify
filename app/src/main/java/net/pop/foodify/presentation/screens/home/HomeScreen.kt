package net.pop.foodify.presentation.screens.home

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.pop.foodify.presentation.screens.helpers.ErrorState
import net.pop.foodify.presentation.screens.helpers.DetailsUiEvent
import net.pop.foodify.presentation.screens.helpers.LoadingState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onMealClicked: (mealId: String) -> Unit
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is DetailsUiEvent.NavigateToDetails -> {
                    onMealClicked(event.mealId)
                }
                is DetailsUiEvent.ShowSnackbar -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    when (uiState) {
        is HomeUiState.Loading -> LoadingState()
        is HomeUiState.Error -> ErrorState((uiState as HomeUiState.Error).message)
        is HomeUiState.Success -> SuccessState(
            filterList = (uiState as HomeUiState.Success).filterList,
            recipes = (uiState as HomeUiState.Success).recipes,
            filterType = (uiState as HomeUiState.Success).filterType,
            onRecipeClicked = viewModel::onMealClicked,
            onFilterItemClicked = viewModel::filterAccordingType
        )
    }
}