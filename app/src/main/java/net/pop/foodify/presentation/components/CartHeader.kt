package net.pop.foodify.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.pop.foodify.presentation.ui.theme.DeepCharcoal
import net.pop.foodify.presentation.ui.theme.FoodifyTypography
import net.pop.foodify.presentation.ui.theme.SageGray
import net.pop.foodify.presentation.ui.theme.Terracotta

@Composable
fun CartHeader(totalItems: Int, totalRecipes: Int, onClearClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column {
            Text("Cart", style = FoodifyTypography.headlineLarge.copy(fontWeight = FontWeight.Bold), color = DeepCharcoal)
            Spacer(modifier = Modifier.height(4.dp))
            Text("$totalItems items from $totalRecipes recipes", style = FoodifyTypography.labelMedium, color = SageGray)
        }
        if (totalItems > 0) {
            Text(
                text = "Clear Cart",
                style = FoodifyTypography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = Terracotta,
                modifier = Modifier.clickable { onClearClick() }.padding(bottom = 2.dp)
            )
        }
    }
}