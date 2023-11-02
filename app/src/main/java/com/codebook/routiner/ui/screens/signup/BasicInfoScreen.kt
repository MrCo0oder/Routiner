package com.codebook.routiner.ui.screens.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.codebook.routiner.model.CreateAccountStateUiEvents
import com.codebook.routiner.utils.Routes.GENDER_SCREEN

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BasicInfoScreen(
    navController: NavHostController = rememberNavController(), viewModel: CreateAccountViewModel
) {
    Scaffold(
        Modifier.background(Color(0xFFF8F7F7)),
        topBar = {
            ToolbarWithBackButton(text = "Create Account") {
                navController.popBackStack()
            }
        },
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(Color(0xFFF8F7F7))
                    .padding(horizontal = 16.dp)
            ) {
                val uiState = viewModel.uiState.collectAsState()
                Column(
                    Modifier
                        .background(Color(0xFFF8F7F7))
                        .verticalScroll(rememberScrollState())
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    TextFieldComponent(
                        "NAME",
                        "Enter Your NAME",
                        10.sp,
                        error = viewModel.validateName(uiState.value.name),
                        default = uiState.value.name
                    ) { name ->
                        viewModel.onEvent(CreateAccountStateUiEvents.Name(name))
                    }
                    TextFieldComponent(
                        "SURNAME",
                        "Enter Your SURNAME",
                        10.sp,
                        error = viewModel.validateName(uiState.value.surName),
                        default = uiState.value.surName
                    ) { name ->
                        viewModel.onEvent(CreateAccountStateUiEvents.SurName(name))
                    }
                    TextFieldComponent(
                        "E-mail",
                        "Enter Your Email",
                        10.sp,
                        error = viewModel.validateEmail(),
                        default = uiState.value.email
                    ) { email ->
                        viewModel.onEvent(CreateAccountStateUiEvents.Email(email))
                    }
                    TextFieldComponent(
                        "Password",
                        "Enter Your Password",
                        10.sp,
                        error = viewModel.validatePassword(),
                        isPassword = true,
                        default = uiState.value.password
                    ) { password ->
                        viewModel.onEvent(CreateAccountStateUiEvents.Password(password))
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                BlueButton(isEnabled =viewModel.isBasicInfoScreenValid()) {
                    navController.navigate(GENDER_SCREEN)
                }
                Spacer(modifier = Modifier.weight(0.3f))
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun BasicInfoScreenPreview() {
    BasicInfoScreen(viewModel = CreateAccountViewModel())
}
