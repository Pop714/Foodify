package net.pop.foodify.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.IosShare
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import net.pop.foodify.model.remote.meal.Meal
import net.pop.foodify.presentation.ui.theme.Terracotta

@Composable
fun HeroImageSection(
    meal: Meal,
    isFavorite: Boolean,
    onBackClick: () -> Unit,
    shareRecipe: (String) -> Unit,
    toggleFav: (Meal) -> Unit,
    favMealEntity: Meal
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
    ) {

        Image(
            rememberAsyncImagePainter(meal.strMealThumb.toString()),
            contentDescription = "Recipe Image",
            modifier = Modifier
                .fillMaxSize()
                .size(256.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.3f))
            ) {
                Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = "Back", tint = Color.White)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                IconButton(
                    onClick = { shareRecipe(meal.idMeal.toString()) },
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White.copy(alpha = 0.3f))
                ) {
                    Icon(Icons.Rounded.IosShare, contentDescription = "Share", tint = Color.White)
                }

                val favIcon = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
                val favTint = if (isFavorite) Terracotta else Color.White

                IconButton(
                    onClick = { toggleFav(favMealEntity) },
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White.copy(alpha = 0.3f))
                ) {
                    Icon(favIcon, contentDescription = "Favorite", tint = favTint)
                }
            }
        }
    }
}