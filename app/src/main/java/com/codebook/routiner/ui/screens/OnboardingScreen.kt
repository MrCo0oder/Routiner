package com.codebook.routiner.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebook.routiner.CircleComponent
import com.codebook.routiner.R

@Composable
fun OnboardingScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0x94EF88ED), Color(0xFF304FFE)
                    ),
                    radius = 2700f,
                    center = Offset(-930f, -930f),
                ),
            )
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize().padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.persons),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        }
        CircleComponent(150.dp, Color(0x40FFFFFF))
        CircleComponent(100.dp, Color(0x33FFFFFF))
        CircleComponent(50.dp, Color(0x1AFFFFFF))
        CircleComponent(10.dp, Color(0x12FFFFFF))

    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(navController = rememberNavController())
}