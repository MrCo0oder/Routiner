package com.codebook.routiner.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebook.routiner.CircleComponent
import com.codebook.routiner.HorizontalPagerScreen
import com.codebook.routiner.R
import com.codebook.routiner.WhiteButtonWithIcon
import com.codebook.routiner.utils.Routes.Login_SCREEN

@Composable
fun OnboardingScreen(navController: NavHostController) {
    BackHandler(enabled = false) {
    }
    Surface {
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
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
                , verticalArrangement = Arrangement.SpaceEvenly
            ) {
                HorizontalPagerScreen()
                WhiteButtonWithIcon(
                    Modifier.fillMaxWidth(),
                    text = "Continue with E-mail",
                    icon = R.drawable.login
                ) {
                    navController.navigate(Login_SCREEN)
                }
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    WhiteButtonWithIcon(
                        modifier = Modifier.weight(1f),
                        text = "Google",
                        icon = R.drawable.google
                    ) {

                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    WhiteButtonWithIcon(
                        modifier = Modifier.weight(1f),
                        text = "Facebook",
                        icon = R.drawable.facebook_logo
                    ) {

                    }
                }
                Text(
                    text = "By continuing you agree Terms of Services & Privacy Policy.",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFAFB4FF),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(navController = rememberNavController())
}