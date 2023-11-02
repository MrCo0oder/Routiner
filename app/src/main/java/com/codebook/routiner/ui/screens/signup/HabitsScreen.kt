package com.codebook.routiner.ui.screens.signup

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.codebook.routiner.BlueButton
import com.codebook.routiner.MultiSelectCard
import com.codebook.routiner.ToolbarWithBackButton
import com.codebook.routiner.model.CreateAccountStateUiEvents

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitsScreen(
    navController: NavController = rememberNavController(), viewModel: CreateAccountViewModel
) {
    val isValidScreen = remember {
        mutableStateOf(viewModel.uiState.value.habitsList.isNotEmpty())
    }

    val uiState = viewModel.uiState.collectAsState()
    Scaffold(
        Modifier.background(Color(0xFFF8F7F7)),
        topBar = {
            ToolbarWithBackButton(text = "Create Account") {
                navController.popBackStack()
            }
        },
        content = { innerPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFFF8F7F7))
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Choose your first habits",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.W500,
                        color = Color(0xFF040415),
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )
                Spacer(modifier = Modifier.weight(0.05f))
                Text(
                    text = "You may add more habits later",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0xFF686873),
                    )
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(viewModel.habitsList.sortedBy { it.id }) { habit ->
                        MultiSelectCard(
                            Modifier,
                            habit.emoji,
                            habit.id,
                            uiState.value.habitsList.contains(habit.id),
                            label = habit.name
                        ) { id, isSelected ->
                            val temp = viewModel.uiState.value.habitsList
                            if (!isSelected) {
                                temp.remove(id)
                            } else {
                                temp.add(id)
                            }
                            viewModel.onEvent(CreateAccountStateUiEvents.Habits(temp))
                            isValidScreen.value = viewModel.uiState.value.habitsList.isNotEmpty()
                        }

                    }
                }
            }
        },
        floatingActionButton = {
            BlueButton("Finish", isEnabled = isValidScreen.value) {
                Log.d("HabitsScreen: ", viewModel.uiState.value.toString())
            }
        }, floatingActionButtonPosition = FabPosition.Center
    )
}