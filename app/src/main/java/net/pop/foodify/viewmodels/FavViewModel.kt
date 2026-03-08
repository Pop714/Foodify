package net.pop.foodify.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.pop.foodify.repos.db.MealRepository
import net.pop.foodify.ui.composables.fav.FavUiState
import net.pop.foodify.ui.composables.home.HomeUiEvent
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val repo: MealRepository
): ViewModel() {

    private val _uiEvent = Channel<HomeUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val uiState: StateFlow<FavUiState> = repo.getAllFavorites()
        .map { favMeals ->
            if (favMeals.isEmpty()) {
                FavUiState.Empty
            } else {
                FavUiState.Success(favMeals)
            }
        }
        .catch { e ->
            Log.e("DB Error", e.message.toString())
            emit(FavUiState.Error("Failed to load favorites."))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = FavUiState.Loading
        )

    fun onMealClicked(mealId: String) {
        viewModelScope.launch {
            _uiEvent.send(HomeUiEvent.NavigateToDetails(mealId))
        }
    }
}