package com.warh.alarmahablante.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CustomButton(texto: String, accionClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = accionClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = Color.Black
        ),
        shape = MaterialTheme.shapes.small,
    ){
        Text(texto.uppercase())
    }
}