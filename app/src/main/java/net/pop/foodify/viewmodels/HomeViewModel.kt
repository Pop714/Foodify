package net.pop.foodify.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import net.pop.foodify.repos.network.MealRepository
import net.pop.foodify.ui.composables.home.HomeUiEvent
import net.pop.foodify.ui.composables.home.HomeUiState

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    // 1. UI State for rendering the screen
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    // 2. UI Events for one-off actions like Navigation or Snackbars
    private val _uiEvent = Channel<HomeUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading

            try {
                // Fetch the default data for the screen
                val categories = repository.getCategories().meals
                val areas = repository.getAreas().meals
                val ingredients = repository.getIngredients().meals
                val defaultMeals = repository.getMealsByCategory()

                _uiState.value = HomeUiState.Success(
                    countries = areas,
                    categories = categories,
                    ingredients = ingredients,
                    mealItems = defaultMeals
                )
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error("Failed to load meals: ${e.message}")
            }
        }
    }

    fun onMealClicked(mealId: String) {
        viewModelScope.launch {
            _uiEvent.send(HomeUiEvent.NavigateToDetails(mealId))
        }
    }

    fun filterAccordingType(type: String, filteredText: String) {
        // 1. Get the current state. If we aren't in Success state, ignore the click.
        val currentState = _uiState.value
        if (currentState !is HomeUiState.Success) return

        viewModelScope.launch {
            try {
                // 2. Fetch the new meals based on the filter type
                val newMeals = when (type) {
                    "category" -> repository.filterMealsWithType("c",filteredText)
                    "area" -> repository.filterMealsWithType("a",filteredText)
                    "ingredient" -> repository.filterMealsWithType("i",filteredText)
                    else -> currentState.mealItems
                }

                // 3. Update ONLY the meals portion of the UI State.
                // By using .copy(), we keep the horizontal chip lists intact so they don't reload!
                _uiState.value = currentState.copy(
                    mealItems = newMeals
                )
            } catch (e: Exception) {
                // Optional: Send an error event to show a Snackbar without breaking the current UI
                _uiEvent.send(HomeUiEvent.ShowSnackbar("Failed to filter meals: ${e.message}"))
            }
        }
    }
}