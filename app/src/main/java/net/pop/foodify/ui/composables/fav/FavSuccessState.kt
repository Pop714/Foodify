package net.pop.foodify.ui.composables.fav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.pop.foodify.model.loacl.FavoriteMealEntity
import net.pop.foodify.model.remote.meals.Meal
import net.pop.foodify.ui.composables.home.HomeMealItem
import net.pop.foodify.ui.theme.Background

@Composable
fun FavSuccessState(
    favMeals: List<FavoriteMealEntity>,
    mealClicked: (String) -> Unit
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(8.dp),
        contentPadding = PaddingValues(vertical = 48.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(favMeals) {
            val favMeal = Meal(idMeal = it.idMeal, strMeal = it.strMeal, strMealThumb = it.strMealThumb)
            HomeMealItem(meal = favMeal, onMealClicked = { mealClicked(favMeal.idMeal.toString()) })
        }
    }
}