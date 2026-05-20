package net.pop.foodify.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import net.pop.foodify.model.remote.meals.Meal
import net.pop.foodify.presentation.ui.theme.DeepCharcoal
import net.pop.foodify.presentation.ui.theme.FoodifyTypography
import net.pop.foodify.presentation.ui.theme.PureWhite

@Composable
fun MealItem(
    modifier: Modifier = Modifier,
    meal: Meal,
    onMealClicked: (String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(PureWhite),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .clickable { onMealClicked(meal.idMeal.toString()) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Image(
                    rememberAsyncImagePainter(meal.strMealThumb.toString()),
                    contentDescription = "Recipe Image",
                    modifier = modifier
                        .fillMaxSize()
                        .size(256.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = meal.strArea.toString(),
                    style = FoodifyTypography.labelMedium.copy(
                        color = DeepCharcoal,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(PureWhite)
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = meal.strMeal.toString(),
                style = FoodifyTypography.labelLarge.copy(
                    color = DeepCharcoal,
                    fontSize = 24.sp
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(12.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            )
        }
    }
}