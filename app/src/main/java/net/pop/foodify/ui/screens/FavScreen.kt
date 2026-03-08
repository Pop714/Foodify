package net.pop.foodify.ui.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.pop.foodify.ui.composables.fav.FavScreenContent
import net.pop.foodify.ui.composables.home.HomeUiEvent
import net.pop.foodify.viewmodels.FavViewModel

@Composable
fun FavScreen(
    viewModel: FavViewModel = hiltViewModel(),
    onMealClicked: (mealId: String) -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is HomeUiEvent.NavigateToDetails -> {
                    onMealClicked(event.mealId)
                }
                is HomeUiEvent.ShowSnackbar -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    FavScreenContent(
        onMealClicked = onMealClicked,
        uiState = uiState
    )
}