package net.pop.foodify.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.pop.foodify.presentation.ui.theme.DeepCharcoal
import net.pop.foodify.presentation.ui.theme.FoodifyTypography
import net.pop.foodify.presentation.ui.theme.Terracotta

@Composable
fun InstructionStepRow(stepNumber: Int, description: String, isActive: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(if (isActive) Terracotta else Color(0xFFEFEBE4)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stepNumber.toString(),
                style = FoodifyTypography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = if (isActive) Color.White else DeepCharcoal
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = description,
            style = FoodifyTypography.bodyMedium,
            color = DeepCharcoal.copy(alpha = 0.8f),
            lineHeight = 24.sp,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}