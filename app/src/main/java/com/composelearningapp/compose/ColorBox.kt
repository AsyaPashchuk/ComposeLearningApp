package com.composelearningapp.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import kotlin.random.Random

@SuppressLint("UnrememberedMutableState")
@Composable
fun ColorBox(
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    changeColor: (Color) -> Unit
) {
    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            changeColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            )
        },
        contentAlignment = Alignment.Center
    ) {
        TextStyleWithAnnotatedString(fontFamily = fontFamily, firstLetter = "C", text = "lick to change the color")
    }
}