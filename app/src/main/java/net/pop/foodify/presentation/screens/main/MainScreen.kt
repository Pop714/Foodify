package net.pop.foodify.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import net.pop.foodify.presentation.navigation.BottomNavItem
import net.pop.foodify.presentation.ui.theme.WarmCream
import net.pop.foodify.utils.isInternetConnected

@Composable
fun MainScreen() {

    val context = LocalContext.current
    val connectivityStatus = isInternetConnected(context)
    val startDestination =
        if (connectivityStatus) BottomNavItem.Home.route else BottomNavItem.Favorite.route

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(WarmCream)
    ) { innerPadding ->
        MainContent(
            modifier = Modifier.padding(innerPadding),
            startDestination = startDestination
        )
    }
}