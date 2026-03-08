package net.pop.foodify.ui.composables.helpers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.pop.foodify.ui.theme.Blue

@Composable
fun Tag(
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    text: String? = null,
    iconTint: Color? = null,
    onIconClicked: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Blue),
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (imageVector != null) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = "Tag",
                    tint = iconTint ?: Color.White,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clickable { onIconClicked() }
                )
            } else {
                MealSubtitleText(text.toString(), modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
            }
        }
    }
}

@Preview
@Composable
fun TagPreview() {
    Tag(text = "Abbas") {}
}