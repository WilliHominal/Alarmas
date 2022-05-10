package com.warh.alarmahablante.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Circle(dia: Int, diaHabilitado: Boolean, alarmaHabilitada: Boolean = true, esClickable: Boolean = true, accionClick: (() -> Unit)? = null) {
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
    val alfaAlarma = if (alarmaHabilitada) 1f else 0.5f
    val colorFondo = if (diaHabilitado) Color.Green.copy(alfaAlarma) else Color.Red.copy(alfaAlarma)
    val colorLetra = if (diaHabilitado) Color.Black.copy(alfaAlarma) else Color.White.copy(alfaAlarma)
    Box(
        modifier =
            if (esClickable)
                Modifier
                    .clip(CircleShape)
                    .background(colorFondo)
                    .size(36.dp)
                    .clickable { accionClick?.let { it() } }
            else
                Modifier
                    .clip(CircleShape)
                    .background(colorFondo)
                    .size(36.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(diaString, color = colorLetra)
    }
}