package com.toadfrogson.clownhub.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.toadfrogson.clownhub.R

@Composable
fun ClownLoader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.clown_lottie))
    LottieAnimation(
        composition = composition,
        speed = 0.5f,
        iterations = LottieConstants.IterateForever,
    )
}