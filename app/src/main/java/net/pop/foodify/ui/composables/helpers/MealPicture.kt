package net.pop.foodify.ui.composables.helpers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun MealPicture(imgUrl: String, modifier: Modifier = Modifier) {
    Image(
        rememberAsyncImagePainter(imgUrl),
        contentDescription = "Profile Image",
        modifier = modifier
            .size(256.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentScale = ContentScale.Crop
    )
}