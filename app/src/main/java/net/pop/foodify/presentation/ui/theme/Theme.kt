package net.pop.foodify.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val FoodifyColorScheme = lightColorScheme(
    primary = Terracotta,
    background = WarmCream,
    surface = PureWhite,
    onBackground = DeepCharcoal,
    onSurface = DeepCharcoal,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = SageGray
)

@Composable
fun FoodifyTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FoodifyColorScheme,
        typography = FoodifyTypography,
        content = content
    )
}