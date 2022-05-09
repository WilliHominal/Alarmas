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
    val colorTexto = Color.Black
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { accionCardClick() },
        elevation = 10.dp,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.secondaryVariant)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Text(alarma.nombre.uppercase(), color = colorTexto)
                Text("#R${alarma.id}", color = colorTexto)
            }
            Surface(
                color = MaterialTheme.colors.secondary
            ){
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(alarma.descripcion, color = colorTexto)
                    Divider(Modifier.padding(vertical = 10.dp), thickness = 1.dp, color = Color.Black)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        alarma.dias.forEachIndexed { indice, habilitada ->
                            Circle(indice, habilitada == 1)
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
        AlarmaRepetitivaCardView(AlarmaRepetitiva(1, "NOMBRE", "DESCRIPCION", Date(), listOf(0,0,0,0,1,1,1), "10:00")) {}
    }
}