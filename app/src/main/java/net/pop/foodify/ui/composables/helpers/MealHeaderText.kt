package net.pop.foodify.ui.composables.helpers

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pop.foodify.ui.theme.PrimaryTextColor
import net.pop.foodify.ui.theme.Rubik

@Composable
fun MealHeaderText(title: String) {
    Text(
        title,
        color = PrimaryTextColor,
        fontFamily = Rubik,
        fontSize = 48.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.fillMaxWidth().padding(6.dp),
        textAlign = TextAlign.Center
    )
}