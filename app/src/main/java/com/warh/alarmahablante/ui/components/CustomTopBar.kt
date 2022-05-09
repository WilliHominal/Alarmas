package com.warh.alarmahablante.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CustomTopBar(titulo: String, permiteVolver: Boolean = false, botonSettingsAccion: (() -> Unit)? = null, botonIzquierdoAccion: () -> Unit) {
    TopAppBar(
        title = { Text(titulo.uppercase(), color = Color.Black) },
        navigationIcon = {
            if (permiteVolver){
                IconButton(onClick = { botonIzquierdoAccion() }) {
                    Icon(Icons.Default.ArrowBack, "Volver", tint = Color.Black)
                }
            } else {
                IconButton(onClick = { botonIzquierdoAccion() }) {
                    Icon(Icons.Default.Menu, "Listar opciones", tint = Color.Black)
                }
            }
        },
        actions = {
            if (!permiteVolver) {
                IconButton(onClick = { botonSettingsAccion?.let{it()} }) {
                    Icon(Icons.Default.Settings, "Configuración", tint = Color.Black)
                }
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}