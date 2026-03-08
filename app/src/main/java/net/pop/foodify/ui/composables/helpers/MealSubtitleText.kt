package net.pop.foodify.ui.composables.helpers

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pop.foodify.ui.theme.Rubik
import net.pop.foodify.ui.theme.SecondaryTextColor

@Composable
fun MealSubtitleText(subtitle: String, modifier: Modifier = Modifier) {
    Text(
        subtitle,
        color = SecondaryTextColor,
        fontFamily = Rubik,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        modifier = Modifier.fillMaxWidth().padding(6.dp),
        textAlign = TextAlign.Center
    )
}