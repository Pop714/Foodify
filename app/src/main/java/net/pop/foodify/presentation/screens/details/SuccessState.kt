package net.pop.foodify.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.pop.foodify.model.remote.meal.Meal
import net.pop.foodify.presentation.components.BottomCartButton
import net.pop.foodify.presentation.components.HeroImageSection
import net.pop.foodify.presentation.components.IngredientCheckRow
import net.pop.foodify.presentation.components.InstructionStepRow
import net.pop.foodify.presentation.components.RecipeHeaderInfo
import net.pop.foodify.presentation.ui.theme.DeepCharcoal
import net.pop.foodify.presentation.ui.theme.FoodifyTypography
import net.pop.foodify.presentation.ui.theme.Terracotta
import net.pop.foodify.presentation.ui.theme.WarmCream
import net.pop.foodify.utils.extractValidIngredients
import net.pop.foodify.utils.extractYoutubeVideoId

@Composable
fun SuccessState(
    meal: Meal,
    isFav: Boolean,
    toggleFav: (Meal) -> Unit,
    onBackClick: () -> Unit,
    shareRecipe: (String) -> Unit,
    openWeb: (String) -> Unit,
    addToCart: (String, String, List<Pair<String, String>>) -> Unit
) {

    val validIngredients = meal.extractValidIngredients()
    var checkedIngredients by remember { mutableStateOf(emptySet<String>()) }

    val favMealEntity = remember(meal) {
        Meal(
            idMeal = meal.idMeal,
            strMeal = meal.strMeal,
            strMealThumb = meal.strMealThumb,
            strArea = meal.strArea
        )
    }

    Scaffold(
        bottomBar = {
            BottomCartButton(
                isEnabled = checkedIngredients.isNotEmpty(),
                onClick = {
                    val mealName = meal.strMeal
                    val mealThumb = meal.strMealThumb
                    val itemsToAdd = validIngredients.filter { checkedIngredients.contains(it.first) }
                    if (itemsToAdd.isNotEmpty()) {
                        addToCart(mealName.toString(), mealThumb.toString(), itemsToAdd)
                        checkedIngredients = emptySet()
                    }
                }
            )
        },
        containerColor = WarmCream
    ) { _ ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {

            item { HeroImageSection(meal, isFav, onBackClick, shareRecipe, toggleFav, favMealEntity) }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-32).dp)
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(WarmCream)
                        .padding(horizontal = 24.dp, vertical = 32.dp)
                ) {

                    RecipeHeaderInfo(meal, openWeb)

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Ingredients",
                            style = FoodifyTypography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = DeepCharcoal
                        )
                        Text(
                            text = "${validIngredients.size} items",
                            style = FoodifyTypography.labelMedium,
                            color = Terracotta
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    validIngredients.forEach { (ingredient, measure) ->
                        IngredientCheckRow(
                            name = ingredient,
                            amount = measure,
                            isChecked = checkedIngredients.contains(ingredient),
                            onCheckedChange = { isChecked ->
                                checkedIngredients = if (isChecked) {
                                    checkedIngredients + ingredient
                                } else {
                                    checkedIngredients - ingredient
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    if (!meal.strYoutube.isNullOrBlank()) {
                        Text(
                            text = "Video Tutorial",
                            style = FoodifyTypography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = DeepCharcoal
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Box(modifier = Modifier.clip(RoundedCornerShape(16.dp))) {
                            YouTubePlayer(
                                extractYoutubeVideoId(meal.strYoutube).toString()
                            )
                        }

                        Spacer(modifier = Modifier.height(32.dp))
                    }

                    Text(
                        text = "Instructions",
                        style = FoodifyTypography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = DeepCharcoal
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    val parsedSteps = meal.strInstructions
                        ?.split(Regex("\r\n|\n|\\.\\s"))
                        ?.filter { it.isNotBlank() && it.length > 3 }
                        ?: emptyList()

                    parsedSteps.forEachIndexed { index, step ->
                        InstructionStepRow(
                            stepNumber = index + 1,
                            description = step.trim().removeSuffix(".") + ".",
                            isActive = index == 0
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }

}