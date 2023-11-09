package com.codebook.routiner.ui.screens.signup

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codebook.routiner.BlueButton
import com.codebook.routiner.TextFieldComponent
import com.codebook.routiner.ToolbarWithBackButton
import com.codebook.routiner.model.CreateAccountStateUiEvents
import com.codebook.routiner.model.GoogleUserModel
import com.codebook.routiner.utils.Constants.USER_MAIL
import com.codebook.routiner.utils.Constants.USER_NAME

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicInfoScreen(
    viewModel: CreateAccountViewModel,
    googleUserModel: GoogleUserModel, goBack: () -> Unit, goNext: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    Log.d("OnboardingScreen", uiState.value.name + "--" + googleUserModel.toString())
    val scrollState = rememberScrollState()
    Scaffold(
        containerColor = Color(0xFFF8F7F7),
        topBar = {
            ToolbarWithBackButton(text = "Create Account ", label = "- Basic Info") {
                goBack()
            }
        },
        content = {
            Column(
                Modifier
                    .padding(it)
                    .background(Color(0xFFF8F7F7))
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState)
            ) {
                val nameList = googleUserModel.name?.split(" ")
                TextFieldComponent(
                    "NAME",
                    "Enter Your Name",
                    10.sp,
                    error = viewModel.validateName(uiState.value.name),
                    default = if (googleUserModel.name.equals(USER_NAME))
                        uiState.value.name else nameList!![0],
                ) { name ->
                    viewModel.onEvent(
                        CreateAccountStateUiEvents.Name(
                            if (googleUserModel.name.equals(USER_NAME))
                                name else nameList!![0]
                        )
                    )
                }
                TextFieldComponent(
                    "SURNAME",
                    "Enter Your Surname",
                    10.sp,
                    error = viewModel.validateName(uiState.value.surName),
                    default = if (googleUserModel.name.equals(USER_NAME)) uiState.value.surName else nameList!![nameList.size - 1],
                ) { name ->
                    viewModel.onEvent(
                        CreateAccountStateUiEvents.SurName(if (googleUserModel.name.equals(USER_NAME)) name else nameList!![nameList.size - 1])
                    )
                }
                TextFieldComponent(
                    "E-mail",
                    "Enter Your Email",
                    10.sp,
                    error = viewModel.validateEmail(),
                    default = if (googleUserModel.email.equals(USER_MAIL)) uiState.value.email else googleUserModel.email!!,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    )
                ) { email ->
                    viewModel.onEvent(
                        CreateAccountStateUiEvents.Email(
                            if (googleUserModel.email.equals(USER_MAIL)) email else googleUserModel.email!!,
                        )
                    )
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

        }, floatingActionButtonPosition = FabPosition.Center, floatingActionButton = {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                BlueButton(
                    isEnabled = viewModel.isBasicInfoScreenValid()
                ) {
                    goNext()
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun BasicInfoScreenPreview() {
    BasicInfoScreen(
        viewModel = CreateAccountViewModel(),
        googleUserModel = GoogleUserModel(null, null), goBack = {}) {}
}
/*
    viewModel.validatePassword() == null && viewModel.validateEmail() == null && viewModel.validateName(uiState.value.surName) == null && viewModel.validateName(uiState.value.name) == null
*/