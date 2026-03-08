package net.pop.foodify.ui.composables.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.Source
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.pop.foodify.model.remote.meal.Meal
import net.pop.foodify.ui.composables.helpers.IngredientTag
import net.pop.foodify.ui.composables.helpers.MealHeaderText
import net.pop.foodify.ui.composables.helpers.MealPicture
import net.pop.foodify.ui.composables.helpers.MealSubtitleText
import net.pop.foodify.ui.composables.helpers.MealTitleText
import net.pop.foodify.ui.composables.helpers.Tag
import net.pop.foodify.ui.composables.helpers.YouTubePlayer
import net.pop.foodify.ui.composables.helpers.extractValidIngredients
import net.pop.foodify.ui.composables.helpers.extractYoutubeVideoId
import net.pop.foodify.ui.composables.helpers.spacers.VerticalSpacer2
import net.pop.foodify.ui.composables.helpers.spacers.VerticalSpacer32
import net.pop.foodify.ui.composables.helpers.spacers.VerticalSpacer64
import net.pop.foodify.ui.composables.helpers.spacers.VerticalSpacer8
import net.pop.foodify.ui.theme.Background
import net.pop.foodify.ui.theme.Grey

@Composable
fun DetailsSuccessState(
    modifier: Modifier = Modifier,
    meal: Meal,
    isFavorite: Boolean,
    openSource: (String) -> Unit,
    shareRecipe: (String) -> Unit,
    openCartPopup: (List<Pair<String, String>>) -> Unit,
    toggleFav: (net.pop.foodify.model.remote.meals.Meal) -> Unit
) {
    val validIngredients = meal.extractValidIngredients()
    val favIcon = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
    val favTint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onSurface
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            VerticalSpacer32()
            MealPicture(meal.strMealThumb.toString(), modifier = Modifier.size(360.dp))
            VerticalSpacer8()
            MealHeaderText(meal.strMeal.toString())
            VerticalSpacer8()
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            ) {
                Tag(text = meal.strCategory.toString(), modifier = Modifier.weight(1f)) {}
                val tags = (meal.strTags ?: "No Tags") as String
                Tag(text = tags, modifier = Modifier.weight(1f)) {}
                Tag(
                    imageVector = Icons.Default.Source,
                    modifier = Modifier.weight(1f),
                    onIconClicked = { openSource(meal.strSource.toString()) })
            }
            VerticalSpacer8()
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            ) {
                Tag(
                    imageVector = Icons.Default.IosShare,
                    modifier = Modifier.weight(1f),
                    onIconClicked = {
                        shareRecipe(meal.idMeal.toString())
                    })
                Tag(
                    imageVector = Icons.Default.AddShoppingCart,
                    modifier = Modifier.weight(1f),
                    onIconClicked = { openCartPopup(validIngredients) })
                Tag(
                    imageVector = favIcon,
                    iconTint = favTint,
                    modifier = Modifier.weight(1f),
                    onIconClicked = {
                        toggleFav(
                            net.pop.foodify.model.remote.meals.Meal(
                                meal.idMeal,
                                meal.strMeal,
                                meal.strMealThumb
                            )
                        )
                    })
            }
            VerticalSpacer8()
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(color = Grey, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MealTitleText("Ingredients and measurements")
                VerticalSpacer8()

                validIngredients.forEach { (ingredient, measure) ->
                    IngredientTag(ingredient, measure)
                    VerticalSpacer2()
                }
            }
        }
        if (meal.strYoutube != null) {
            item {
                VerticalSpacer8()
                MealTitleText("Youtube Video")
                VerticalSpacer8()
                YouTubePlayer(extractYoutubeVideoId(meal.strYoutube).toString())
            }
        }
        item {
            VerticalSpacer8()
            MealTitleText("Instructions")
            VerticalSpacer8()
            MealSubtitleText(
                meal.strInstructions
                    ?.replace("\\r", "")
                    ?.replace("\\n", "\n")
                    .orEmpty(),
                modifier = Modifier.padding(8.dp)
            )
            VerticalSpacer64()
        }
    }
}