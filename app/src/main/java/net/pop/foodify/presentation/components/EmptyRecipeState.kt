package net.pop.foodify.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.pop.foodify.presentation.ui.theme.FoodifyTypography
import net.pop.foodify.presentation.ui.theme.Terracotta

@Composable
fun EmptyRecipeState(
    isFav: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = if (isFav) Icons.Rounded.FavoriteBorder else Icons.Rounded.Search,
            contentDescription = "Empty Search",
            modifier = Modifier.size(64.dp),
            tint = Terracotta
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = if (isFav) "No favorite recipes found" else "No recipes found",
            style = FoodifyTypography.titleMedium.copy(
                color = Color(0xFF1C1A17)
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text =
                if (isFav) "We couldn't find any favorite recipes for you. Try adding some by clicking the heart icon."
                else "We couldn't find any recipes for this category. Try selecting a different filter above.",
            style = FoodifyTypography.bodyMedium.copy(
                color = Color(0xFF7C7770)
            ),
            textAlign = TextAlign.Center
        )
    }
}