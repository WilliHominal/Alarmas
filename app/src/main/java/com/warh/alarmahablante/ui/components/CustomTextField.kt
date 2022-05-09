package com.warh.alarmahablante.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CustomTextField(
    texto: String,
    textoAyuda: String,
    habilitado: Boolean,
    iconoTrasero: @Composable (() -> Unit)? = null,
    accionClick: (() -> Unit)? = null,
    accionCambioDeValor: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = texto,
        onValueChange = accionCambioDeValor,
        textStyle = TextStyle(color = Color.Black),
        label = {
            Text(textoAyuda, style = MaterialTheme.typography.caption)
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { accionClick?.let{it()} },
        enabled = habilitado,
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = Color.Black,
            focusedIndicatorColor = Color.Black
        ),
        keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text),
        trailingIcon = iconoTrasero
    )
}