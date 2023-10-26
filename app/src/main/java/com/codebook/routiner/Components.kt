package com.codebook.routiner

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.colorResource
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
import com.codebook.routiner.ui.screens.loginWithEmail.LoginStateUiEvents


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

@Preview(showBackground = true, backgroundColor = 0x00000)
@Composable
fun OnBoardingComponentPreview() {
    HorizontalPagerScreen()
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

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
private fun WhiteButtonWithIconPreview() {
    WhiteButtonWithIcon(Modifier.fillMaxWidth(), "Facebook", R.drawable.facebook_logo) {

    }
}

@Composable
fun ToolbarWithBackButton(text: String, onClick: () -> Unit) {
    Row(
        Modifier
            .background(Color.White)
            .padding(bottom = 16.dp, top = 12.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        IconButton(
            onClick = { onClick() },
//            colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Black),
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

@Preview(showBackground = true, backgroundColor = 0x0000000)
@Composable
fun ToolbarWithBackButtonPreview() {
    ToolbarWithBackButton("Continue with E-mail") {

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
    val showPassword = remember { mutableStateOf(false) }
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
            fontWeight = FontWeight.Normal,
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
            trailingIcon = if (isPassword) {
                {
                    val (icon, iconColor) = if (showPassword.value) {
                        Pair(
                            Icons.Default.Done,
                            colorResource(id = R.color.purple_200)
                        )
                    } else {
                        Pair(
                            Icons.Default.Clear,
                            colorResource(id = R.color.purple_500)
                        )
                    }

                    IconButton(onClick = { showPassword.value = !showPassword.value }) {
                        Icon(
                            icon,
                            contentDescription = "Visibility",
                            tint = iconColor
                        )
                    }
                }
            } else null,
            supportingText = {
                if (!error.isNullOrEmpty())
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Info, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = error, color = MaterialTheme.colorScheme.error)
                    }
            },
            onValueChange = {
                value = it.trim()
                onValueChange(it.trim())
            },
            placeholder = {
                Text(text = placeholder, fontSize = 18.sp)
            },
            textStyle = TextStyle.Default.copy(fontSize = 24.sp, color = Color(0xFF000000)),
            singleLine = true,
            keyboardActions = KeyboardActions {
                localFocusManager.clearFocus()
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextFieldComponentPreview() {
    TextFieldComponent("Name", "", 18.sp, default = "", error = "sadad") {}
}