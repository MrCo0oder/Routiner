package com.codebook.routiner

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircleComponent(padding: Dp, color: Color) {
    Box(
        modifier = Modifier
            .padding(padding)
            .border(
                width = 1.dp, color = color, shape = CircleShape
            )
            .aspectRatio(1f)
            .fillMaxSize()
    )
}

@Composable
fun OnBoardingComponent(image: Int, header: String, subHeading: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background(Color.Transparent)
            .padding(20.dp)
            .height(500.dp)
    ) {
        Image(
            painter = painterResource(image),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            text = header, style = TextStyle(
                fontSize = 40.sp,
                lineHeight = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFFFFF),
            ), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = subHeading, textAlign = TextAlign.Start, style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Light,
                color = Color(0xFFD7D9FF),
            ), modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerScreen() {
    val pageCount = 3
    val pagerState = rememberPagerState(
        initialPage = 0,
    ) {
        pageCount
    }
    Column {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            state = pagerState,
            userScrollEnabled = true,
        ) { current ->
            when (current) {
                0 -> {
                    OnBoardingComponent(
                        image = R.drawable.persons,
                        header = "Create\nGood Habits",
                        subHeading = "Change your life by slowly adding new healthy habits and sticking to them.",
                    )
                }

                1 -> {
                    OnBoardingComponent(
                        image = R.drawable.cards,
                        header = "Track\nYour Progress",
                        subHeading = "Everyday you become one step closer to your goal. Don’t give up!",
                    )
                }

                else -> {
                    OnBoardingComponent(
                        image = R.drawable.strike,
                        header = "Stay Together\nand Strong",
                        subHeading = "Find friends to discuss common topics. Complete challenges together.",
                    )
                }
            }
        }
        Indicator(pagerState = pagerState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Indicator(pagerState: PagerState) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) Color(0xFFFFFFFF) else Color(0xFF888EFF)
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)
            )
        }
    }
}

@Composable
fun WhiteButtonWithIcon(modifier: Modifier, text: String, icon: Int, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = modifier.wrapContentHeight(),
        colors = ButtonDefaults.buttonColors(Color.White, Color.Gray)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = text, style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF040415),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}


@Composable
fun ToolbarWithBackButton(text: String, onClick: () -> Unit) {
    Row(
        Modifier
            .background(Color.White)
            .padding(bottom = 16.dp, top = 46.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        IconButton(
            onClick = { onClick() },
            modifier = Modifier
                .padding(start = 15.dp)
                .size(48.dp)
                .aspectRatio(1f)
                .border(0.5.dp, Color.LightGray, RoundedCornerShape(16.dp))
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = text, style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF040415),
            )
        )


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    label: String,
    placeholder: String = "",
    labelSize: TextUnit,
    labelColor: Color = Color(0xFF000000),
    default: String,
    error: String?,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    var value by remember {
        mutableStateOf(default)
    }
    val showPassword = remember { mutableStateOf(true) }
    val showIcon = remember { mutableStateOf(false) }
    val localFocusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = label,
            color = labelColor,
            fontSize = labelSize,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value.trim(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xff3BA935),
                unfocusedIndicatorColor = Color(0xffCDCDD0)
            ),
            isError = !error.isNullOrEmpty(),
            visualTransformation = if (isPassword && showPassword.value) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon =
            {
                if (showIcon.value) {
                    if (isPassword) {
                        val icon = if (showPassword.value) R.drawable.show else R.drawable.hide
                        IconButton(onClick = { showPassword.value = !showPassword.value }) {
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = "Visibility",
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                value = ""
                                onValueChange("")
                                showIcon.value = false
                            },
                        ) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.close),
                                contentDescription = null,
                            )
                        }
                    }
                }
            },
            supportingText = {
                if (!error.isNullOrEmpty())
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = error, color = MaterialTheme.colorScheme.error)
                    }
            },
            onValueChange = {
                value = it.trim()
                onValueChange(it.trim())
                if (it.isNotEmpty())
                    showIcon.value = true
            },
            placeholder = {
                Text(text = placeholder, fontSize = 18.sp)
            },
            textStyle = TextStyle.Default.copy(fontSize = 24.sp, color = Color(0xFF000000)),
            singleLine = true,
            keyboardActions = KeyboardActions {
                localFocusManager.clearFocus()
                if (!isPassword)
                    showIcon.value = false
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectableCard(
    modifier: Modifier,
    emoji: String,
    id: Int,
    isSelected: Boolean,
    color: Color = Color.White,
    label: String = "Male",
    onClick: (Int, Boolean) -> Unit
) {
    var selected = isSelected
    Card(
        onClick = {
            if (selected) onClick(-1, true) else onClick(id, false)
            selected = !selected
        },
        colors = CardDefaults.cardColors(color, contentColor = MaterialTheme.colorScheme.tertiary),
        modifier = modifier
            .padding(16.dp)
            .height(134.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(9),
                    color = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(0.8f))
                Text(
                    text = emoji,
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 48.sp,
                        fontWeight = FontWeight.W700,
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = label,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = Modifier.weight(0.8f))
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiSelectCard(
    modifier: Modifier,
    emoji: String,
    id: Int,
    isSelected: Boolean,
    color: Color = Color.White,
    label: String = "Male",
    onClick: (Int, Boolean) -> Unit
) {
    val selected = remember {
        mutableStateOf(isSelected)
    }
    Card(
        onClick = {
            selected.value = !selected.value
            onClick(id, selected.value)
        },
        colors = CardDefaults.cardColors(color, contentColor = MaterialTheme.colorScheme.tertiary),
        modifier = modifier
            .padding(16.dp)
            .height(134.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(9),
                    color = if (selected.value) MaterialTheme.colorScheme.primary else Color.Transparent
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(0.8f))
                Text(
                    text = emoji,
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 48.sp,
                        fontWeight = FontWeight.W700,
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = label,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = Modifier.weight(0.8f))
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun SelectableCardPreview() {
    Column {
//        SelectableCard(Modifier.weight(1f), emoji = "\uD83E\uDD37\uD83C\uDFFB\u200D️", 0, true) {
//
//        }
//        SelectableCard(Modifier.weight(1f), emoji = "\uD83D\uDE4B\uD83C\uDFFB\u200D♀️", 1, false) {
//        }
    }
}

@Composable
fun BlueButton(text: String = "Next", isEnabled: Boolean, onClick: () -> Unit) {
    Button(
        enabled = isEnabled,
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            Color(0xFF3843FF),
            disabledContainerColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Text(
            text = text, Modifier.padding(16.dp), style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.W500,
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
            )
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun BlueButtonPreview() {
    BlueButton(isEnabled = true) {

    }
}
