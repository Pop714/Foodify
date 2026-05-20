package net.pop.foodify.presentation.screens.home

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
import net.pop.foodify.data.remote.meals.MealsResponse
import net.pop.foodify.presentation.components.EmptyRecipeState
import net.pop.foodify.presentation.components.FilterChipRow
import net.pop.foodify.presentation.components.FoundRecipeState
import net.pop.foodify.presentation.ui.theme.FoodifyTypography
import net.pop.foodify.presentation.ui.theme.Terracotta
import net.pop.foodify.presentation.ui.theme.WarmCream

@Composable
fun SuccessState(
    filterList: List<Pair<String, String>>,
    recipes: List<MealsResponse>,
    filterType: String,
    onRecipeClicked: (String) -> Unit,
    onFilterItemClicked: (String, String) -> Unit
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

        Spacer(modifier = Modifier.height(25.dp))

        FilterChipRow(
            items = filterList,
            selectedItemName = filterType,
            onChipClick = { filterType, filterItem ->
                onFilterItemClicked(
                    filterType,
                    filterItem
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (recipes.isEmpty()) {
            EmptyRecipeState()
        } else {
            FoundRecipeState(
                recipes = recipes,
                onRecipeClicked = onRecipeClicked,
                text = if (filterType == "All") "Recommended For You" else filterType
            )
        }
    }
}