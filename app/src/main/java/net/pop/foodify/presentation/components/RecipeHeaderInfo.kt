package net.pop.foodify.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.pop.foodify.model.remote.meal.Meal
import net.pop.foodify.presentation.ui.theme.DeepCharcoal
import net.pop.foodify.presentation.ui.theme.FoodifyTypography
import net.pop.foodify.presentation.ui.theme.Terracotta

@Composable
fun RecipeHeaderInfo(
    meal: Meal,
    openSource: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryPill(meal.strCategory.toString())
            CategoryPill(meal.strArea.toString())
            meal.strTags.toString().split(",").firstOrNull()?.let { firstTag ->
                if (firstTag.isNotBlank() && firstTag != "null") CategoryPill(firstTag)
            }

            Spacer(modifier = Modifier.weight(1f))

            if (!meal.strSource.isNullOrBlank()) {
                IconButton(onClick = { openSource(meal.strSource) }) {
                    Icon(Icons.Rounded.Language, contentDescription = "Source", tint = Terracotta)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = meal.strMeal.toString(),
            style = FoodifyTypography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = DeepCharcoal
        )
    }
}
