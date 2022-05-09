package com.warh.alarmahablante.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomCheckbox(texto: String, activado: Boolean, accionAlActivar: (Boolean) -> Unit) {

    val colorFondo = if (activado) Color.Green else Color.Red

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .background(colorFondo, MaterialTheme.shapes.medium)
                .size(45.dp)
                .clickable {
                    accionAlActivar(true)
                },
            contentAlignment = Alignment.Center
        ){
            if (activado){
                Box(
                    Modifier
                        .background(color = Color.Black, shape = CircleShape)
                        .size(8.dp)
                )
            }
        }
        Text(texto, color = Color.Black)
    }
}