package com.warh.alarmahablante.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@SuppressLint("UnnecessaryComposedModifier")
@Composable
fun Circle(dia: Int, habilitada: Boolean, modifier: Modifier = Modifier, accionClick: (() -> Unit)? = null) {
    val diaString = when (dia){
        0 -> "D"
        1 -> "L"
        2 -> "M"
        3 -> "M"
        4 -> "J"
        5 -> "V"
        6 -> "S"
        else -> throw IndexOutOfBoundsException("No existe el día $dia")
    }
    val colorFondo = if (habilitada) Color.Green else Color.Red
    val colorLetra = if (habilitada) Color.Black else Color.White
    Box(
        modifier = modifier.composed {
                clip(CircleShape)
                .background(colorFondo)
                    .size(36.dp)
                    .clickable { accionClick?.let{ it() } }
        },
        contentAlignment = Alignment.Center,
    ) {
        Text(diaString, color = colorLetra)
    }
}