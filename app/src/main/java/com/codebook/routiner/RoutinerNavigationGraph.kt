package com.codebook.routiner

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codebook.routiner.model.GoogleUserModel
import com.codebook.routiner.ui.screens.OnboardingScreen
import com.codebook.routiner.ui.screens.SignInGoogleViewModel
import com.codebook.routiner.ui.screens.SignInGoogleViewModelFactory
import com.codebook.routiner.ui.screens.SplashScreen
import com.codebook.routiner.ui.screens.loginWithEmail.LoginWithEmailScreen
import com.codebook.routiner.ui.screens.signup.BasicInfoScreen
import com.codebook.routiner.ui.screens.signup.CreateAccountViewModel
import com.codebook.routiner.ui.screens.signup.GenderScreen
import com.codebook.routiner.ui.screens.signup.HabitsScreen
import com.codebook.routiner.utils.Constants.USER_MAIL
import com.codebook.routiner.utils.Constants.USER_NAME
import com.codebook.routiner.utils.Routes.BASIC_INFO_SCREEN
import com.codebook.routiner.utils.Routes.GENDER_SCREEN
import com.codebook.routiner.utils.Routes.HABITS_SCREEN
import com.codebook.routiner.utils.Routes.Login_SCREEN
import com.codebook.routiner.utils.Routes.ONBOARDING_SCREEN
import com.codebook.routiner.utils.Routes.SPLASH_SCREEN

@Composable
fun RoutinerNavigationGraph() {
    val navController = rememberNavController()
    val createAccountViewModel = CreateAccountViewModel()
    val mSignInViewModel: SignInGoogleViewModel = viewModel(
        factory = SignInGoogleViewModelFactory(LocalContext.current.applicationContext as Application)
    )
    NavHost(navController = navController, startDestination = SPLASH_SCREEN) {
        composable(SPLASH_SCREEN) {
            SplashScreen(navController)
        }
        composable(ONBOARDING_SCREEN) {
            OnboardingScreen(mSignInViewModel, navController) {
                navController.navigate(buildString {
                    append(BASIC_INFO_SCREEN)
                    append("/")
                    append(it.first)
                    append("/")
                    append(it.second)
                })
            }
        }
        composable(
            "${BASIC_INFO_SCREEN}/{${USER_NAME}}/{${USER_MAIL}}",
            arguments = listOf(
                navArgument(name = USER_NAME) {
                    type = NavType.StringType
                },
                navArgument(name = USER_MAIL) {
                    type = NavType.StringType
                }
            ),
        ) {
            it.arguments?.apply {
                BasicInfoScreen(
                    viewModel = createAccountViewModel,
                    googleUserModel = GoogleUserModel(
                        getString(USER_NAME),
                        getString(USER_MAIL)
                    ), goBack = { navController.popBackStack() }
                ) {
                    navController.navigate(GENDER_SCREEN)
                }
            }

        }
        composable(Login_SCREEN) {
            LoginWithEmailScreen(navController) {
                navController.navigate(buildString {
                    append(BASIC_INFO_SCREEN)
                    append("/")
                    append(it.first)
                    append("/")
                    append(it.second)
                })
            }
        }
        /*        composable(BASIC_INFO_SCREEN) {
                    BasicInfoScreen(navController, createAccountViewModel,GoogleUserModel("",""))
                }
                */
        composable(GENDER_SCREEN) {
            GenderScreen(navController, createAccountViewModel) {
                navController.navigate(HABITS_SCREEN)
            }
        }
        composable(HABITS_SCREEN) {
            HabitsScreen(navController, createAccountViewModel)
        }
    }
}


