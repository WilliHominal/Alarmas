package com.warh.alarmahablante.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomAlertDialog(habilitada: Boolean = true, accionCerrar: () -> Unit, accionDeshabilitar: () -> Unit, accionModificar: () -> Unit, accionEliminar: () -> Unit) {
    Dialog(
        onDismissRequest = accionCerrar,
    ) {
        Column(
            Modifier
                .background(Color.Black.copy(0.0f))
                .padding(end = 10.dp, start = 10.dp, bottom = 10.dp)
                .clip(MaterialTheme.shapes.large)
        ) {
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
            ) {
                IconButton(
                    onClick = accionCerrar,
                ) {
                    Icon(
                            Icons.Default.Close,
                            "Cerrar",
                            tint = MaterialTheme.colors.primaryVariant,
                            modifier = Modifier.clip(CircleShape).background(Color.Black.copy(0.7f))
                        )

                }
            }
            CustomButton(texto = if (habilitada) "Deshabilitar" else "Habilitar") {
                accionDeshabilitar()
            }
            Box(
                Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()) {
                CustomButton(texto = "Modificar") {
                    accionModificar()
                }
            }
            CustomButton(texto = "Eliminar") {
                accionEliminar()
            }
        }
    }

}
