package com.codebook.routiner.ui.screens.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.codebook.routiner.SelectableCard
import com.codebook.routiner.ToolbarWithBackButton
import com.codebook.routiner.model.CreateAccountStateUiEvents
import com.codebook.routiner.utils.Routes
import com.codebook.routiner.utils.Routes.HABITS_SCREEN

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GenderScreen(
    navController: NavHostController,
    viewModel: CreateAccountViewModel
) {
    val uiState = viewModel.uiState.collectAsState()

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
                Text(
                    text = "Choose your gender",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.W500,
                        color = Color(0xFF040415),
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                )
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SelectableCard(
                        Modifier.weight(1f),
                        "\uD83E\uDD37\uD83C\uDFFB\u200D️",
                        0,
                        uiState.value.gender == 0, label = "Male"
                    ) { id, _ ->
                        viewModel.onEvent(CreateAccountStateUiEvents.Gender(id))
                    }
                    SelectableCard(
                        Modifier.weight(1f),
                        "\uD83D\uDE4B\uD83C\uDFFB\u200D♀️",
                        1,
                        uiState.value.gender == 1, label = "Female"
                    ) { id, _ ->
                        viewModel.onEvent(CreateAccountStateUiEvents.Gender(id))
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                BlueButton(isEnabled =viewModel.isGenderScreenValid()) {
                    navController.navigate(Routes.HABITS_SCREEN)
                }
                Spacer(modifier = Modifier.weight(0.1f))
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun BasicInfoScreenPreview() {
    GenderScreen(rememberNavController(), viewModel = CreateAccountViewModel())
}
