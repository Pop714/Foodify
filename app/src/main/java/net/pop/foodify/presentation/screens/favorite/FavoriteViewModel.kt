package net.pop.foodify.presentation.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.pop.foodify.data.repos.RecipeRepository
import net.pop.foodify.presentation.screens.helpers.DetailsUiEvent

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    dbRepo: RecipeRepository
) : ViewModel() {

    private val _uiEvent = Channel<DetailsUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val uiState: StateFlow<FavoriteUiState> = dbRepo.getAllFavorites()
        .map { favMeals ->
            FavoriteUiState.Success(favMeals) as FavoriteUiState
        }
        .catch { e ->
            emit(FavoriteUiState.Error("Failed to load favorites.\n Error is ${e.message}"))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = FavoriteUiState.Loading
        )

    fun onRecipeClicked(id: String) {
        viewModelScope.launch {
            _uiEvent.send(DetailsUiEvent.NavigateToDetails(id))
        }
    }

}