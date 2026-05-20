package net.pop.foodify.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pop.foodify.data.local.entity.FavoriteRecipeEntity
import net.pop.foodify.data.remote.meals.MealsResponse
import net.pop.foodify.model.remote.meals.Meal
import net.pop.foodify.presentation.ui.theme.DeepCharcoal
import net.pop.foodify.presentation.ui.theme.FoodifyTypography
import net.pop.foodify.presentation.ui.theme.WarmCream

@Composable
fun FoundRecipeState(
    recipes: List<MealsResponse> = emptyList(),
    favRecipes: List<FavoriteRecipeEntity> = emptyList(),
    text: String,
    onRecipeClicked: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {

        stickyHeader {
            Text(
                text = text,
                style = FoodifyTypography.headlineLarge.copy(
                    color = DeepCharcoal,
                    fontSize = 26.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(WarmCream)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }

        if (recipes.isNotEmpty()) {
            recipes.forEach {
                items(it.meals) { meal ->
                    MealItem(
                        meal = meal,
                        onMealClicked = { onRecipeClicked(meal.idMeal.toString()) })
                }
            }
        } else {
            items(favRecipes) {
                val favMeal = Meal(
                    idMeal = it.id,
                    strMeal = it.strMeal,
                    strMealThumb = it.strMealThumb,
                    strArea = it.strArea
                )
                MealItem(
                    meal = favMeal,
                    onMealClicked = { onRecipeClicked(favMeal.idMeal.toString()) })
            }
        }
    }
}