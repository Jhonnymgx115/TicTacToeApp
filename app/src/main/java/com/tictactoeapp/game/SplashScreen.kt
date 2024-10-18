package com.tictactoeapp.game

import android.util.Log
import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {
    Log.d("TicTacToe", "Splash: Se ejecuto Splash ")
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.98f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )

        navController.navigate("mode_selection") {
            popUpTo("splash_screen") {
                inclusive = true
            }
        }
    }

    Splash(scale)

}

@Composable
fun Splash(scale: Animatable<Float, AnimationVector1D>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.close_bold_svgrepo_com),
                contentDescription = "X",
                modifier = Modifier
                    .size(180.dp)
                    .scale(scale.value)
            )
            Image(
                painter = painterResource(id = R.drawable.circle_svgrepo_com),
                contentDescription = "X",
                modifier = Modifier
                    .size(160.dp)
                    .scale(scale.value)
            )
        }
    }
}

