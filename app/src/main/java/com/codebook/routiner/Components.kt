package com.codebook.routiner

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CircleComponent(padding: Dp, color: Color) {
    Box(
        modifier = Modifier
            .padding(padding)
            .border(
                width = 1.dp,
                color = color,
                shape = CircleShape
            )
            .aspectRatio(1f)
            .fillMaxSize()
    )
}