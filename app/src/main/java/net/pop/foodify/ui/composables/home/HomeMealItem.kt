package net.pop.foodify.ui.composables.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.pop.foodify.model.remote.meals.Meal
import net.pop.foodify.ui.composables.helpers.MealPicture
import net.pop.foodify.ui.composables.helpers.MealTitleText
import net.pop.foodify.ui.composables.helpers.spacers.VerticalSpacer8
import net.pop.foodify.ui.theme.Grey

@Composable
fun HomeMealItem(meal: Meal, modifier: Modifier = Modifier, onMealClicked: (String) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(Grey),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .clickable { onMealClicked(meal.idMeal.toString()) },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MealPicture(meal.strMealThumb.toString())
            VerticalSpacer8()
            MealTitleText(meal.strMeal.toString())
        }
    }
}

@Preview
@Composable
fun ItemPreview() {
    HomeMealItem(
        Meal(
            strMeal = "Algerian Kefta (Meatballs)",
            strMealThumb = "https://www.themealdb.com/images/media/meals/8rfd4q1764112993.jpg",
            idMeal = "53281"
        )
    ) { }
}