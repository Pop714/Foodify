package net.pop.foodify.presentation.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pop.foodify.data.local.entity.FavoriteRecipeEntity
import net.pop.foodify.presentation.components.EmptyRecipeState
import net.pop.foodify.presentation.components.FoundRecipeState
import net.pop.foodify.presentation.ui.theme.FoodifyTypography
import net.pop.foodify.presentation.ui.theme.Terracotta
import net.pop.foodify.presentation.ui.theme.WarmCream

@Composable
fun SuccessState(
    recipes: List<FavoriteRecipeEntity>,
    onMealClicked: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WarmCream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Foodify",
            style = FoodifyTypography.headlineLarge.copy(
                color = Terracotta,
                fontSize = 36.sp
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (recipes.isEmpty()) {
            EmptyRecipeState(isFav = true)
        } else {
            FoundRecipeState(
                favRecipes = recipes,
                onRecipeClicked = onMealClicked,
                text = "Your Favorite Meals"
            )
        }
    }
}