package net.pop.foodify.ui.composables.helpers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import net.pop.foodify.ui.composables.helpers.spacers.HorizontalSpacer12

@Composable
fun IngredientTag(ingredient: String?, measure: String?, modifier: Modifier = Modifier) {
    if (!ingredient.isNullOrBlank()) {

        // Build the dynamic image URL provided by MealDB
        val imageUrl = "https://www.themealdb.com/images/ingredients/$ingredient-Small.png"

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left side: Image + Ingredient Name
            Row(verticalAlignment = Alignment.CenterVertically) {

                MealPicture(
                    imageUrl,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
                HorizontalSpacer12()

                Text(
                    text = ingredient, style = MaterialTheme.typography.bodyLarge
                )
            }

            Text(
                text = measure.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}