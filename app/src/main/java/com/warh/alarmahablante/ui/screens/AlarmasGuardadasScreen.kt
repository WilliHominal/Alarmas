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
import com.warh.alarmahablante.ui.components.CustomAlertDialog
import com.warh.alarmahablante.ui.components.CustomTopBar
import com.warh.alarmahablante.viewmodel.AlarmaViewModel

@Composable
fun AlarmasGuardadasScreen(navController: NavController, viewModel: AlarmaViewModel) {

    var alarmas by remember { mutableStateOf(listOf<Alarma>()) }

    var dialogoAbierto by remember { mutableStateOf(false) }
    var alarmaSeleccionada by remember { mutableStateOf<Alarma?>(null) }

    viewModel.listaAlarmasRepetitivas.observe( LocalLifecycleOwner.current ){
        val mutableSet: MutableList<Alarma> = alarmas.filterIsInstance<AlarmaUnica>().toMutableList()
        mutableSet.addAll(it)
        alarmas = mutableSet.sortedByDescending { x -> x.fechaCreacion }
    }

    viewModel.listaAlarmasUnicas.observe( LocalLifecycleOwner.current ){
        val mutableSet: MutableList<Alarma> = alarmas.filterIsInstance<AlarmaRepetitiva>().toMutableList()
        mutableSet.addAll(it)
        alarmas = mutableSet.sortedByDescending { x -> x.fechaCreacion }
    }

    if (dialogoAbierto) {
        CustomAlertDialog(
            accionCerrar = { dialogoAbierto = false },
            accionDeshabilitar = {
                alarmaSeleccionada?.let {
                    it.habilitada = false
                    when(alarmaSeleccionada){
                        is AlarmaRepetitiva -> viewModel.agregarAlarma(alarmaSeleccionada as AlarmaRepetitiva)
                        is AlarmaUnica -> viewModel.agregarAlarma(alarmaSeleccionada as AlarmaUnica)
                    }
                }
                dialogoAbierto = false
            },
            accionModificar = { /*TODO*/ },
            accionEliminar = { /*TODO*/ }
        )
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
            //TODO Mejorar UI cuando no hay alarmas
            Text("No hay alarmas guardadas", color = Color.Black)
        } else {
            LazyColumn {
                items(alarmas){ alarma ->
                    when(alarma) {
                        //TODO Agregar accion al presionar sobre la alarma (Dialog con Deshabilitar, Editar, Eliminar)
                        is AlarmaRepetitiva -> AlarmaRepetitivaCardView(alarma) {
                            if (alarma.habilitada) {
                                alarmaSeleccionada = alarma
                                dialogoAbierto = true
                            } else {
                                alarma.habilitada = true
                                viewModel.agregarAlarma(alarma)
                                alarmaSeleccionada = alarma
                            }
                        }
                        is AlarmaUnica -> AlarmaUnicaCardView(alarma) {
                            alarmaSeleccionada = alarma
                            if (alarma.habilitada)
                                dialogoAbierto = true
                            else {
                                alarma.habilitada = true
                                viewModel.agregarAlarma(alarma)
                            }
                        }
                    }
                }
                item(){
                    Spacer(Modifier.padding(bottom = 35.dp))
                }
            }
        }
    }
}