package net.pop.foodify.ui.composables.home

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
import net.pop.foodify.model.remote.areas.Area
import net.pop.foodify.model.remote.categories.Category
import net.pop.foodify.model.remote.ingredients.Ingredient
import net.pop.foodify.model.remote.meals.MealsResponse
import net.pop.foodify.ui.composables.helpers.spacers.VerticalSpacer8
import net.pop.foodify.ui.theme.Background

@Composable
fun SuccessState(
    categories: List<Category>,
    areas: List<Area>,
    ingredients: List<Ingredient>,
    meals: List<MealsResponse>,
    mealClicked: (String) -> Unit,
    filterTyped: (String, String) -> Unit
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(8.dp),
        contentPadding = PaddingValues(vertical = 48.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        item {
            FilterChipRow(
                items = areas.map { it.strArea.toString() },
                onChipClick = { selectedArea -> filterTyped("area", selectedArea) }
            )
            VerticalSpacer8()
        }

        item {
            FilterChipRow(
                items = categories.map { it.strCategory.toString() },
                onChipClick = { selectedArea -> filterTyped("category", selectedArea) }
            )
            VerticalSpacer8()
        }

        item {
            FilterChipRow(
                items = ingredients.map { it.strIngredient.toString() },
                onChipClick = { selectedArea -> filterTyped("ingredient", selectedArea) }
            )
            VerticalSpacer8()
        }

        meals.forEach {
            items(it.meals) { meal ->
                HomeMealItem(meal = meal, onMealClicked = { mealClicked(meal.idMeal.toString()) })
            }
        }
    }
}