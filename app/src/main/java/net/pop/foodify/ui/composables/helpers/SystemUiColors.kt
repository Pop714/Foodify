@file:Suppress("DEPRECATION")

package net.pop.foodify.ui.composables.helpers

import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import net.pop.foodify.ui.theme.Black

@Composable
fun SystemUiColors() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setNavigationBarColor(Black, false)
    systemUiController.setStatusBarColor(Black, false)
}