package net.pop.foodify.presentation.screens.main

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import net.pop.foodify.presentation.navigation.BottomNavItem
import net.pop.foodify.presentation.screens.cart.CartScreen
import net.pop.foodify.presentation.screens.details.DetailsScreen
import net.pop.foodify.presentation.screens.favorite.FavoriteScreen
import net.pop.foodify.presentation.screens.helpers.LoadingState
import net.pop.foodify.presentation.screens.home.HomeScreen

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    startDestination: String
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isTopLevelScreen = currentRoute == BottomNavItem.Home.route ||
            currentRoute == BottomNavItem.Favorite.route ||
            currentRoute == BottomNavItem.Cart.route

    var isVisibleByScroll by remember { mutableStateOf(true) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < -10) {
                    isVisibleByScroll = false
                }
                if (available.y > 10) {
                    isVisibleByScroll = true
                }
                return Offset.Zero
            }
        }
    }

    val isBottomBarVisible = isTopLevelScreen && isVisibleByScroll

    Box(modifier = modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection),
            enterTransition = { fadeIn(animationSpec = tween(700)) },
            exitTransition = { fadeOut(animationSpec = tween(700)) },
            popEnterTransition = { fadeIn(animationSpec = tween(700)) },
            popExitTransition = { fadeOut(animationSpec = tween(700)) }
        ) {
            composable(BottomNavItem.Home.route) {
                LoadingState()
                HomeScreen(
                    onMealClicked = { selectedMealId ->
                        navController.navigate("details/$selectedMealId")
                    }
                )
            }
            composable(BottomNavItem.Favorite.route) {
                FavoriteScreen(
                    onRecipeClicked = { selectedMealId ->
                        navController.navigate("details/$selectedMealId")
                    }
                )
            }
            composable(BottomNavItem.Cart.route) {
                CartScreen()
            }
            composable(
                route = "details/{mealId}",
                arguments = listOf(navArgument("mealId") { type = NavType.StringType }),
                deepLinks = listOf(
                    navDeepLink { uriPattern = "foodify://details/{mealId}" }
                ),
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    ) + fadeIn(tween(700))
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    ) + fadeOut(tween(700))
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    ) + fadeIn(tween(700))
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    ) + fadeOut(tween(700))
                }
            ) { backStackEntry ->
                val mealId = backStackEntry.arguments?.getString("mealId")
                if (mealId != null) {
                    DetailsScreen(
                        id = mealId,
                        onBackClicked = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = isBottomBarVisible,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            FloatingBottomNavigationBar(
                navController = navController,
                isVisible = true
            )
        }
    }
}