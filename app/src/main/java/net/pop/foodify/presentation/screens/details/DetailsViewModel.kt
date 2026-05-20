package net.pop.foodify.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import net.pop.foodify.data.local.entity.CartEntity
import net.pop.foodify.data.local.entity.FavoriteRecipeEntity
import net.pop.foodify.data.repos.MealRepository
import net.pop.foodify.data.repos.RecipeRepository
import net.pop.foodify.model.remote.meal.Meal
import net.pop.foodify.utils.Constants

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo: MealRepository,
    private val dbRepo: RecipeRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<DetailsUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _isFav = MutableStateFlow(false)
    val isFav = _isFav.asStateFlow()

    fun getMealDetailsById(id: String) {
        viewModelScope.launch {
            _uiState.value = DetailsUiState.Loading
            try {
                val meal = repo.getMealById(id)
                _uiState.value = DetailsUiState.Success(meal.meals)
            } catch (e: Exception) {
                _uiState.value = DetailsUiState.Error("Failed to get recipe details ${e.message}\nCheck your internet connection. ")
            }
        }

        viewModelScope.launch {
            dbRepo.isFav(id).collect {
                _isFav.value = it
            }
        }
    }

    fun toggleFavorite(meal: Meal) {
        viewModelScope.launch {
            try {
                val mealId = meal.idMeal.toString()
                if (_isFav.value) {
                    dbRepo.removeFavoriteRecipe(mealId)
                    _uiEvent.send(DetailsUiEvent.ShowToast("Removed from favorites."))
                } else {
                    val entity = FavoriteRecipeEntity(
                        id = mealId,
                        strMeal = meal.strMeal ?: "Unknown Meal",
                        strMealThumb = meal.strMealThumb ?: "",
                        strArea = meal.strArea ?: ""
                    )
                    dbRepo.insertFavoriteRecipe(entity)
                    _uiEvent.send(DetailsUiEvent.ShowToast("${meal.strMeal} added to favorites!"))
                }
            } catch (e: Exception) {
                _uiEvent.send(DetailsUiEvent.ShowToast("Failed to update favorites. ${e.message}"))
            }
        }
    }

    fun openSourceUrl(url: String?) {
        if (!url.isNullOrBlank()) {
            viewModelScope.launch {
                _uiEvent.send(DetailsUiEvent.OpenRecipeWeb(url))
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

    fun addRecipeItemsToCart(name: String, thumb: String, items: List<Pair<String, String>>) {
        viewModelScope.launch {
            try {
                for (item in items) {
                    val cartItem = CartEntity(
                        mealName = name,
                        mealThumb = thumb,
                        ingredient = item.first,
                        measure = item.second
                    )
                    dbRepo.insertCartItem(cartItem)
                }
                _uiEvent.send(DetailsUiEvent.ShowToast("Successfully added item to cart."))
            } catch (e: Exception) {
                _uiEvent.send(DetailsUiEvent.ShowToast("Failed to add ${e.message}"))
            }
        }
    }

}