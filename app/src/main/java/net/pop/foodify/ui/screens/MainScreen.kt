package net.pop.foodify.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.pop.foodify.ui.composables.helpers.FloatingBottomNavigationBar
import net.pop.foodify.uils.BottomNavItem
import net.pop.foodify.uils.isInternetConnected

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val connectivityStatus = isInternetConnected(context)
    val startDestination =
        if (connectivityStatus) BottomNavItem.Home.route else BottomNavItem.Favorite.route

    val navController = rememberNavController()
    MainScreenContent(navController, startDestination)
}

@Composable
fun MainScreenContent(navController: NavHostController, startDestination: String) {


    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    onMealClicked = { selectedMealId ->
                        navController.navigate("details/$selectedMealId")
                    }
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}