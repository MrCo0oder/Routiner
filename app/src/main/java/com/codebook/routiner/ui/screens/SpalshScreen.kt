package com.codebook.routiner.ui.screens

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.codebook.routiner.CircleComponent
import com.codebook.routiner.R
import com.codebook.routiner.utils.Routes
import com.codebook.routiner.utils.Routes.ONBOARDING_SCREEN
import com.codebook.routiner.utils.Routes.SPLASH_SCREEN
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0x59EF88ED), Color(0xFF304FFE)
                    ),
                    radius = 2700f,
                    center = Offset(-930f, -930f),
                ),
            )
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircleComponent(150.dp, Color(0x40FFFFFF))
        CircleComponent(100.dp, Color(0x33FFFFFF))
        CircleComponent(50.dp, Color(0x1AFFFFFF))
        CircleComponent(10.dp, Color(0x12FFFFFF))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = null)
            Image(painter = painterResource(id = R.drawable.ic_routiner), contentDescription = null)
        }
    }
    GlobalScope.launch {
        delay(2000)
        withContext(Dispatchers.Main){
                navController.navigate(ONBOARDING_SCREEN)
                {
                    popUpTo(SPLASH_SCREEN) { inclusive = true }
                }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}