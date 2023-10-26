package com.codebook.routiner.ui.screens.loginWithEmail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebook.routiner.TextFieldComponent
import com.codebook.routiner.ToolbarWithBackButton

@Composable
fun LoginWithEmailScreen(navController: NavHostController = rememberNavController()) {
    val viewModel = LoginWithEmailViewModel()
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .background(Color(0xFFF8F7F7))
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                ToolbarWithBackButton(text = "Continue with E-mail") {
                }
            }

            TextFieldComponent(
                "E-mail",
                "Enter Your Email",
                10.sp,
                error = viewModel.validateEmail(),
                default = viewModel.uiState.value.email
            ) {
                viewModel.onEvent(LoginStateUiEvents.Email(it))
            }
            TextFieldComponent(
                "Password",
                "Enter Your Password",
                10.sp,
                error = viewModel.validatePassword(),
                isPassword = true,
                default = viewModel.uiState.value.password
            ) {
                viewModel.onEvent(LoginStateUiEvents.Password(it))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginWithEmailScreenPreview() {
    LoginWithEmailScreen()
}
