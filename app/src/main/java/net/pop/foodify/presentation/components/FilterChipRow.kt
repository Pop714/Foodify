package net.pop.foodify.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.pop.foodify.presentation.ui.theme.DeepCharcoal
import net.pop.foodify.presentation.ui.theme.FoodifyTypography
import net.pop.foodify.presentation.ui.theme.PureWhite
import net.pop.foodify.presentation.ui.theme.Terracotta

@Composable
fun FilterChipRow(
    items: List<Pair<String, String>>,
    selectedItemName: String,
    onChipClick: (String, String) -> Unit
) {
    Column {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(items) { item ->
                val isSelected = item.second == selectedItemName
                Text(
                    text = item.second,
                    style = FoodifyTypography.bodyMedium.copy(
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    ),
                    color = if (isSelected) PureWhite else DeepCharcoal,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (isSelected) Terracotta else Color.Transparent)
                        .clickable { onChipClick(item.first, item.second) }
                        .border(
                            width = 1.dp,
                            color = if (isSelected) Color.Transparent else Terracotta.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                )
            }
        }
    }
}