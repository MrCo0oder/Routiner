package com.codebook.routiner.ui.screens.loginWithEmail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebook.routiner.BlueButton
import com.codebook.routiner.TextFieldComponent
import com.codebook.routiner.ToolbarWithBackButton
import com.codebook.routiner.utils.Constants
import com.codebook.routiner.utils.Constants.USER_MAIL
import com.codebook.routiner.utils.Constants.USER_NAME
import com.codebook.routiner.utils.Routes
import com.codebook.routiner.utils.Routes.BASIC_INFO_SCREEN

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginWithEmailScreen(
    navController: NavHostController = rememberNavController(),
    userData: (Pair<String, String>) -> Unit
) {
    val viewModel = LoginWithEmailViewModel()
    Scaffold(
        topBar = {
            ToolbarWithBackButton(text = "Continue with E-mail") {
                navController.popBackStack()
            }
        },
        content = {
            Column(
                Modifier
                    .background(Color(0xFFF8F7F7))
                    .padding(horizontal = 16.dp)
                    .padding(it)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
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
                Text(
                    text = "I forgot my password",
                    Modifier.padding(horizontal = 16.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.W500,
                        color = Color(0xFF686873),
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Don’t have account?",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                        )
                    )
                    TextButton(onClick = {
                            userData(Pair(USER_NAME, USER_MAIL))
                    }) {
                        Text(
                            text = "Let’s create!",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.W500,
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                BlueButton(isEnabled = viewModel.isValidScreen()) {
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
private fun LoginWithEmailScreenPreview() {
    LoginWithEmailScreen() {}
}
