package net.pop.foodify.presentation.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pop.foodify.data.local.entity.CartEntity
import net.pop.foodify.presentation.components.CartHeader
import net.pop.foodify.presentation.components.RecipeCartGroup
import net.pop.foodify.presentation.ui.theme.FoodifyTypography
import net.pop.foodify.presentation.ui.theme.Terracotta
import net.pop.foodify.presentation.ui.theme.WarmCream

@Composable
fun SuccessState(
    groupedCartItems: Map<CartHeaderModel, List<CartEntity>>,
    onRemoveItem: (CartEntity) -> Unit,
    onClearCart: () -> Unit
) {
    val totalItems = groupedCartItems.values.sumOf { it.size }
    val totalRecipes = groupedCartItems.keys.size

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

        CartHeader(
            totalItems = totalItems,
            totalRecipes = totalRecipes,
            onClearClick = onClearCart
        )

        if (groupedCartItems.isEmpty()) {
            EmptyCartState()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                groupedCartItems.forEach { (recipeModel, ingredients) ->
                    item(key = recipeModel.mealName) {
                        RecipeCartGroup(
                            recipeName = recipeModel.mealName,
                            recipeThumb = recipeModel.mealThumb,
                            ingredients = ingredients,
                            onRemoveItem = onRemoveItem
                        )
                    }
                }
            }
        }
    }
}