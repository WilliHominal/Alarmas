package com.warh.alarmahablante.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.warh.alarmahablante.model.Alarma
import com.warh.alarmahablante.model.AlarmaRepetitiva
import com.warh.alarmahablante.model.AlarmaUnica
import com.warh.alarmahablante.ui.components.AlarmaRepetitivaCardView
import com.warh.alarmahablante.ui.components.AlarmaUnicaCardView
import com.warh.alarmahablante.ui.components.CustomTopBar
import com.warh.alarmahablante.viewmodel.AlarmaViewModel

@Composable
fun AlarmasGuardadasScreen(navController: NavController, viewModel: AlarmaViewModel) {

    var alarmas by remember { mutableStateOf(setOf<Alarma>()) }

    viewModel.listaAlarmasRepetitivas.observe( LocalLifecycleOwner.current ){
        val mutableSet = alarmas.toMutableSet()
        mutableSet.addAll(it)
        alarmas = mutableSet.sortedByDescending { x -> x.fechaCreacion }.toSet()
    }

    viewModel.listaAlarmasUnicas.observe( LocalLifecycleOwner.current ){
        val mutableSet = alarmas.toMutableSet()
        mutableSet.addAll(it)
        alarmas = mutableSet.sortedByDescending { x -> x.fechaCreacion }.toSet()
    }

    Scaffold(
        topBar = {
            CustomTopBar(titulo = "alarmas guardadas") {}
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("ConfigurarAlarma") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 7.dp,
                    pressedElevation = 11.dp
                ),
                modifier = Modifier.border(color = Color(0xFFF084A8), width = 2.dp, shape = CircleShape),
                contentColor = Color.Black,
            ) {
                Icon(Icons.Default.Add, "Agregar")
            }
        },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        if (alarmas.isEmpty()){
            Text("No hay alarmas guardadas", color = Color.Black)
        } else {
            LazyColumn {
                items(alarmas.toList()){ alarma ->
                    when(alarma) {
                        is AlarmaRepetitiva -> AlarmaRepetitivaCardView(alarma) {}
                        is AlarmaUnica -> AlarmaUnicaCardView(alarma) {}
                    }
                }
                item(){
                    Spacer(Modifier.padding(bottom = 35.dp))
                }
            }
        }
    }
}