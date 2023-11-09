package com.codebook.routiner.ui.screens.signup

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.codebook.routiner.MultiSelectCard
import com.codebook.routiner.ToolbarWithBackButton
import com.codebook.routiner.model.CreateAccountStateUiEvents
import com.codebook.routiner.ui.theme.Blue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitsScreen(
    navController: NavController = rememberNavController(), viewModel: CreateAccountViewModel
) {
    val isValidScreen = remember {
        mutableStateOf(viewModel.uiState.value.habitsList.isNotEmpty())
    }
    val scrollState = rememberLazyGridState()
    val uiState = viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        Modifier
            .background(Color(0xFFF8F7F7))
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ToolbarWithBackButton(
                text = "Create Account ",
                label = "- Habits",
                scrollBehavior = scrollBehavior
            ) {
                navController.popBackStack()
            }

        },
        content = { innerPadding ->
            Column(
                Modifier
                    .padding(innerPadding)
                    .background(Color(0xFFF8F7F7))
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    state = scrollState,
                ) {
                    itemsIndexed(viewModel.habitsList.sortedBy { it.id }) { index, habit ->
                        Column {
                            MultiSelectCard(
                                Modifier.fillMaxWidth(),
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
                                isValidScreen.value =
                                    viewModel.uiState.value.habitsList.isNotEmpty()
                            }
                            if (index == viewModel.habitsList.size - 1) {
                                Spacer(modifier = Modifier.height(85.dp))
                            }
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            val c = LocalContext.current
            ExtendedFloatingActionButton(
                onClick = {
                    if (isValidScreen.value) {
                        Log.d("HabitsScreen: ", viewModel.uiState.value.toString())
                        Toast.makeText(c, viewModel.uiState.value.toString(), Toast.LENGTH_SHORT).show()
                    }
                },
                icon = { Icon(Icons.Filled.AccountCircle, "Extended floating action button.") },
                text = { Text(text = "Create Account", color = Color.White) },
                expanded = isValidScreen.value,
                containerColor = Blue,
                modifier = Modifier.padding(20.dp)
            )
        }, floatingActionButtonPosition = FabPosition.End
    )
}

//@Composable
//fun Header(nestedScroll: Modifier) {
//    Column(nestedScroll) {
//        Text(
//            text = "Choose your first habits",
//            style = TextStyle(
//                fontSize = 18.sp,
//                lineHeight = 24.sp,
//                fontWeight = FontWeight.W500,
//                color = Color(0xFF040415),
//            ), modifier = Modifier
//                .background(Color.Transparent)
//                .fillMaxWidth()
//                .padding(top = 20.dp)
//        )
//        Spacer(
//            modifier = Modifier
//                .height(3.dp)
//                .background(Color.Transparent)
//        )
//        Text(
//            text = "You may add more habits later",
//            modifier = Modifier.background(Color.Transparent),
//            style = TextStyle(
//                fontSize = 14.sp,
//                lineHeight = 20.sp,
//                fontWeight = FontWeight.W400,
//                color = Color(0xFF686873),
//            )
//        )
//    }
//}
