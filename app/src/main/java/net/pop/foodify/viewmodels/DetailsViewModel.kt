package net.pop.foodify.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import net.pop.foodify.model.loacl.CartEntity
import net.pop.foodify.model.loacl.FavoriteMealEntity
import net.pop.foodify.model.remote.meals.Meal
import net.pop.foodify.repos.network.MealRepository
import net.pop.foodify.ui.composables.details.DetailsUiEvent
import net.pop.foodify.ui.composables.details.DetailsUiState
import net.pop.foodify.uils.Constants

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MealRepository,
    private val dbRepo: net.pop.foodify.repos.db.MealRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<DetailsUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _showCartPopup = MutableStateFlow(false)
    val showCartPopup = _showCartPopup.asStateFlow()

    private val _cartIngredients = MutableStateFlow<List<Pair<String, String>>>(emptyList())
    val cartIngredients = _cartIngredients.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    fun getMealById(id: String) {
        viewModelScope.launch {
            _uiState.value = DetailsUiState.Loading

            try {
                val meal = repository.getMealById(id)

                _uiState.value = DetailsUiState.Success(
                    meal = meal.meals
                )
            } catch (e: Exception) {
                _uiState.value = DetailsUiState.Error("Failed to load meals: ${e.message}")
            }
        }

        viewModelScope.launch {
            dbRepo.isFav(id).collect { isFav ->
                _isFavorite.value = isFav
            }
        }
    }

    fun openSourceUrl(url: String?) {
        if (!url.isNullOrBlank()) {
            viewModelScope.launch {
                _uiEvent.send(DetailsUiEvent.OpenWebBrowser(url))
            }
        } else {
            viewModelScope.launch {
                _uiEvent.send(DetailsUiEvent.ShowToast("No source URL available for this recipe."))
            }
        }
    }

    fun shareRecipe(mealId: String) {
        val shareUrl = "${Constants.SHARE_URL}$mealId"
        viewModelScope.launch {
            _uiEvent.send(DetailsUiEvent.ShareRecipe(shareUrl))
        }
    }

    fun toggleFavorite(meal: Meal) {
        viewModelScope.launch {
            try {
                val mealId = meal.idMeal.toString()

                if (_isFavorite.value) {
                    // It IS a favorite, so remove it
                    dbRepo.removeFavoriteMeal(mealId)
                    _uiEvent.send(DetailsUiEvent.ShowToast("Removed from favorites."))
                } else {
                    // It is NOT a favorite, so add it
                    val entity = FavoriteMealEntity(
                        idMeal = mealId,
                        strMeal = meal.strMeal ?: "Unknown Meal",
                        strMealThumb = meal.strMealThumb ?: ""
                    )
                    dbRepo.insertFavoriteMeal(entity)
                    _uiEvent.send(DetailsUiEvent.ShowToast("${meal.strMeal} added to favorites!"))
                }
            } catch (e: Exception) {
                _uiEvent.send(DetailsUiEvent.ShowToast("Failed to update favorites. ${e.message}"))
            }
        }
    }

    fun addToCart(ingredient: String, measure: String) {
        viewModelScope.launch {
            try {
                dbRepo.insertCartItem(CartEntity(ingredient = ingredient, measure = measure))
            } catch (e: Exception) {
                _uiEvent.send(DetailsUiEvent.ShowToast("Failed to add to cart. ${e.message}"))
            }
        }
    }

    fun openCartPopup(content: List<Pair<String, String>>) {
        _cartIngredients.value = content
        _showCartPopup.value = true // This will trigger the Compose UI to show the dialog
    }

    fun closeCartPopup() {
        _showCartPopup.value = false
        _cartIngredients.value = emptyList() // Clear it out to save memory
    }
}