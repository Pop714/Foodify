package net.pop.foodify.uils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector = Icons.Default.Home, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Search : BottomNavItem("search", Icons.Default.Search, "Search")
    object Favorite : BottomNavItem("favorite", Icons.Default.Favorite, "Favorite")
    object Cart : BottomNavItem("cart", Icons.Default.ShoppingCart, "Cart")
    object CartPopUp : BottomNavItem(route = "cartPopUp", label = "CartPopUp")
    object Details : BottomNavItem(route = "details", label = "Details")
}