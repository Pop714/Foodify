package net.pop.foodify.ui.composables.helpers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import net.pop.foodify.R
import net.pop.foodify.ui.theme.Black

@Composable
fun LoadingState() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_food))

    Box(modifier = Modifier.fillMaxSize().background(Black), contentAlignment = Alignment.Center) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(200.dp)
        )
    }
}