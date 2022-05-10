package com.warh.alarmahablante.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warh.alarmahablante.model.AlarmaRepetitiva
import java.util.*

@Composable
fun AlarmaRepetitivaCardView(alarma: AlarmaRepetitiva, accionCardClick: () -> Unit) {
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
                Text("#R${alarma.id}", color = colorTexto)
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
                        alarma.dias.forEachIndexed { indice, habilitada ->
                            Circle(indice, habilitada == 1, alarma.habilitada, esClickable = false)
                        }
                        Text(alarma.hora, color = colorTexto)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlarmaRepetitivaCardViewPreview(){
    MaterialTheme {
        AlarmaRepetitivaCardView(AlarmaRepetitiva(1, "NOMBRE", "DESCRIPCION", Date(), true, listOf(0,0,0,0,1,1,1), "10:00")) {}
    }
}