package net.pop.foodify.presentation.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.RemoveShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.pop.foodify.presentation.ui.theme.DeepCharcoal
import net.pop.foodify.presentation.ui.theme.FoodifyTypography
import net.pop.foodify.presentation.ui.theme.SageGray

@Composable
fun EmptyCartState() {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Rounded.RemoveShoppingCart, contentDescription = null, modifier = Modifier.size(64.dp), tint = SageGray)
        Spacer(modifier = Modifier.height(24.dp))
        Text("Your cart is empty", style = FoodifyTypography.titleMedium, color = DeepCharcoal)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Add ingredients from your favorite recipes to easily shop for them later.",
            style = FoodifyTypography.bodyMedium,
            color = SageGray,
            textAlign = TextAlign.Center
        )
    }
}