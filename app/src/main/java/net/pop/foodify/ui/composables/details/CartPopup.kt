package net.pop.foodify.ui.composables.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import net.pop.foodify.ui.composables.helpers.spacers.VerticalSpacer8

@Composable
fun CartPopupDialog(
    ingredients: List<Pair<String, String>>,
    onDismiss: () -> Unit,
    onAddToCart: (String, String) -> Unit
) {
    // 1. Keep track of the ingredients that have been added
    val addedItems = remember { mutableStateListOf<String>() }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Add to Cart",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                VerticalSpacer8()

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(ingredients) { (ingredient, measure) ->
                        // 2. Check if this specific ingredient is in our added list
                        val isAdded = addedItems.contains(ingredient)

                        IngredientCartRow(
                            ingredient = ingredient,
                            measure = measure,
                            isAdded = isAdded, // Pass the state down
                            onAddClick = {
                                onAddToCart(ingredient, measure)
                                addedItems.add(ingredient) // Mark as added when clicked
                            }
                        )
                        HorizontalDivider(
                            Modifier,
                            DividerDefaults.Thickness,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun IngredientCartRow(
    ingredient: String,
    measure: String,
    isAdded: Boolean, // New parameter
    onAddClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = ingredient.trim(),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = measure.trim(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        IconButton(
            onClick = onAddClick,
            enabled = !isAdded, // Disable the button if it's already added
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(
                    if (isAdded) MaterialTheme.colorScheme.surfaceVariant
                    else MaterialTheme.colorScheme.primaryContainer
                )
        ) {
            Icon(
                // Swap the icon to a Checkmark when added
                imageVector = if (isAdded) Icons.Default.Check else Icons.Default.Add,
                contentDescription = if (isAdded) "Added to Cart" else "Add to Cart",
                // Mute the icon color when added
                tint = if (isAdded) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                else MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}