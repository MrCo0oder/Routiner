package com.codebook.routiner

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codebook.routiner.ui.screens.OnboardingScreen
import com.codebook.routiner.ui.screens.SplashScreen
import com.codebook.routiner.ui.screens.loginWithEmail.LoginWithEmailScreen
import com.codebook.routiner.utils.Routes.Login_SCREEN
import com.codebook.routiner.utils.Routes.ONBOARDING_SCREEN
import com.codebook.routiner.utils.Routes.SPLASH_SCREEN

@Composable
fun RoutinerNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SPLASH_SCREEN) {
        composable(SPLASH_SCREEN) {
            SplashScreen(navController)
        }
        composable(ONBOARDING_SCREEN) {
            OnboardingScreen(navController)
        }
        composable(Login_SCREEN) {
            LoginWithEmailScreen(navController)
        }
    }

}


