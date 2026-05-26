package net.pop.foodify.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import net.pop.foodify.permissions.RequestNotificationPermission
import net.pop.foodify.presentation.screens.main.MainScreen
import net.pop.foodify.presentation.ui.theme.FoodifyTheme
import net.pop.foodify.worker.scheduleDailyMealNotification

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodifyTheme {
                RequestNotificationPermission(
                    onPermissionGranted = {
                        scheduleDailyMealNotification(applicationContext)
                    }
                )
                MainScreen()
            }
        }
    }
}