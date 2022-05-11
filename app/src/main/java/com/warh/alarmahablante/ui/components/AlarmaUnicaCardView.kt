package com.warh.alarmahablante.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.warh.alarmahablante.model.AlarmaUnica
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AlarmaUnicaCardView(alarma: AlarmaUnica, accionCardClick: () -> Unit) {
    val alfaAlarma = if (alarma.habilitada) 1f else 0.5f
    val colorTexto = Color.Black.copy(alfaAlarma)
    val colorFondoSecundario = MaterialTheme.colors.secondary.copy(alfaAlarma)
    val colorFondoSecundarioVariante = MaterialTheme.colors.secondaryVariant.copy(alfaAlarma)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { accionCardClick() },
        elevation = 10.dp,
        shape = MaterialTheme.shapes.medium,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .background(colorFondoSecundarioVariante)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Text(alarma.nombre.uppercase(), color = colorTexto)
                Text("#U${alarma.id}", color = colorTexto)
            }
            Surface(
                color = colorFondoSecundario
            ){
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(alarma.descripcion, color = colorTexto)
                    Divider(Modifier.padding(vertical = 10.dp), thickness = 1.dp, color = colorTexto)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text("Fecha: ${formatearFecha(alarma.fecha)} hs.", color = colorTexto)
                    }
                }
            }
        }
    }
}

fun formatearFecha(date: Date): String {
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault())
    return dateFormatter.format(date)
}