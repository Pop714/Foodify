package net.pop.foodify.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import net.pop.foodify.data.local.entity.CartEntity
import net.pop.foodify.presentation.ui.theme.DeepCharcoal
import net.pop.foodify.presentation.ui.theme.FoodifyTypography
import net.pop.foodify.presentation.ui.theme.WarmCream

@Composable
fun RecipeCartGroup(
    recipeName: String,
    recipeThumb: String,
    ingredients: List<CartEntity>,
    onRemoveItem: (CartEntity) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp, start = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (recipeThumb.isNotEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(recipeThumb),
                    contentDescription = "$recipeName Thumbnail",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
            }

            Text(
                text = recipeName,
                style = FoodifyTypography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = DeepCharcoal,
                modifier = Modifier.weight(1f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            ingredients.forEachIndexed { index, item ->
                CartIngredientRow(
                    name = item.ingredient,
                    measure = item.measure,
                    onItemChecked = { onRemoveItem(item) }
                )

                if (index < ingredients.lastIndex) {
                    HorizontalDivider(
                        color = WarmCream,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}