package net.pop.foodify.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import net.pop.foodify.presentation.ui.theme.DeepCharcoal
import net.pop.foodify.presentation.ui.theme.FoodifyTypography
import net.pop.foodify.presentation.ui.theme.SageGray
import net.pop.foodify.presentation.ui.theme.Terracotta

@Composable
fun CartIngredientRow(
    name: String,
    measure: String,
    onItemChecked: () -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }

    LaunchedEffect(isChecked) {
        if (isChecked) {
            delay(300)
            onItemChecked()
            isChecked = false
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isChecked = true }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(if (isChecked) Terracotta else Color.Transparent)
                .border(
                    width = 2.dp,
                    color = if (isChecked) Terracotta else Color.LightGray.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(6.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isChecked) {
                Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = name,
            style = FoodifyTypography.bodyMedium.copy(fontWeight = if (isChecked) FontWeight.Normal else FontWeight.Medium),
            color = if (isChecked) SageGray else DeepCharcoal,
            textDecoration = if (isChecked) TextDecoration.LineThrough else null,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = measure,
            style = FoodifyTypography.labelMedium,
            color = if (isChecked) SageGray.copy(alpha = 0.5f) else SageGray
        )
    }
}