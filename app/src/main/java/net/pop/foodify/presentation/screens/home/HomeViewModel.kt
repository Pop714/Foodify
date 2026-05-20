package net.pop.foodify.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import net.pop.foodify.data.repos.MealRepository
import net.pop.foodify.presentation.screens.helpers.DetailsUiEvent

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<DetailsUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading

            try {
                val categories = repository.getCategories().meals
                val areas = repository.getAreas().meals
                val ingredients = repository.getIngredients().meals
                val defaultMeals = repository.getMealsByCategory()

                val filterList: List<Pair<String, String>> =
                    listOf("all" to "All") +
                    categories.map { "category" to it.strCategory.toString() } +
                    areas.map { "area" to it.strArea.toString() } +
                    ingredients.map { "ingredient" to it.strIngredient.toString() }

                _uiState.value = HomeUiState.Success(
                    filterList = filterList,
                    recipes = defaultMeals,
                    filterType = "All"
                )
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error("Failed to load recipes: ${e.message}")
            }
        }
    }

    fun onMealClicked(mealId: String) {
        viewModelScope.launch {
            _uiEvent.send(DetailsUiEvent.NavigateToDetails(mealId))
        }
    }

    fun filterAccordingType(type: String, filteredText: String) {
        val currentState = _uiState.value
        if (currentState !is HomeUiState.Success) return

        viewModelScope.launch {
            try {
                val newMeals = when (type) {
                    "category" -> repository.filterMealsWithType("c", filteredText)
                    "area" -> repository.filterMealsWithType("a", filteredText)
                    "ingredient" -> repository.filterMealsWithType("i", filteredText)
                    "all" -> repository.getMealsByCategory()
                    else -> currentState.recipes
                }

                _uiState.value = currentState.copy(
                    recipes = newMeals,
                    filterType = filteredText
                )
            } catch (e: Exception) {
                _uiEvent.send(DetailsUiEvent.ShowSnackbar("Failed to filter meals: ${e.message}"))
                _uiState.value = currentState.copy(
                    recipes = emptyList(),
                    filterType = filteredText
                )
            }
        }
    }
}