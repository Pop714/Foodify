package net.pop.foodify.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.pop.foodify.ui.composables.helpers.FloatingBottomNavigationBar
import net.pop.foodify.uils.BottomNavItem

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    MainScreenContent(navController)
}

@Composable
fun MainScreenContent(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    onMealClicked = { selectedMealId ->
                        navController.navigate("details/$selectedMealId")
                    }
                )
            }
            composable(BottomNavItem.Search.route) {
                // Search Screen
                DummyScreen(
                    "Search",
                    Color(0xFFFF9800),
                    Color(0xFFF44336)
                )
            }
            composable(BottomNavItem.Favorite.route) {
                FavScreen(
                    onMealClicked = { selectedMealId ->
                        navController.navigate("details/$selectedMealId")
                    }
                )
            }
            composable(BottomNavItem.Cart.route) {
                CartScreen()
            }
            composable(
                route = "details/{mealId}",
                arguments = listOf(navArgument("mealId") { type = NavType.StringType })
            ) { backStackEntry ->
                val mealId = backStackEntry.arguments?.getString("mealId")
                if (mealId != null) {
                    DetailsScreen(mealId = mealId)
                }
            }
        }
        FloatingBottomNavigationBar(
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun DummyScreen(title: String, color1: Color, color2: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(color1, color2))),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}