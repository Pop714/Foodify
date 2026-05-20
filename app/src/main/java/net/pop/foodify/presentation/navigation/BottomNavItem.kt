package net.pop.foodify.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector = Icons.Default.Home, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Favorite : BottomNavItem("favorite", Icons.Default.Favorite, "Favorite")
    object Cart : BottomNavItem("cart", Icons.Default.ShoppingCart, "Cart")
}