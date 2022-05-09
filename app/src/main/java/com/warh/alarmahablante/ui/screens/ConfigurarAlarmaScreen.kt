package com.warh.alarmahablante.ui.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.warh.alarmahablante.model.AlarmaRepetitiva
import com.warh.alarmahablante.model.AlarmaUnica
import com.warh.alarmahablante.ui.components.*
import com.warh.alarmahablante.viewmodel.AlarmaViewModel
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Composable
fun ConfigurarAlarmaScreen(navController: NavController, viewModel: AlarmaViewModel) {
    var nombreAlarma by remember { mutableStateOf("") }
    var descripcionAlarma by remember { mutableStateOf("") }
    var repetitivaActivado by remember { mutableStateOf(true) }
    var diasRepetitiva by remember { mutableStateOf(listOf(0,0,0,0,0,0,0)) }
    var diaUnica by remember { mutableStateOf("") }
    var horaAlarma by remember { mutableStateOf("") }

    val context = LocalContext.current

    val calendario = Calendar.getInstance()
    val calendarioAnio = calendario.get(Calendar.YEAR)
    val calendarioMes = calendario.get(Calendar.MONTH)
    val calendarioDia = calendario.get(Calendar.DAY_OF_MONTH)
    val calendarioHora = calendario.get(Calendar.HOUR_OF_DAY)
    val calendarioMinuto = calendario.get(Calendar.MINUTE)

    val selectorFecha = DatePickerDialog(
        context,
        { _: DatePicker, anio: Int, mes: Int, dia: Int ->
            diaUnica = "$dia/${mes+1}/$anio"
        }, calendarioAnio, calendarioMes, calendarioDia
    )

    val selectorHora = TimePickerDialog(
        context,
        {_, hora : Int, minuto: Int ->
            horaAlarma = "${if (hora <= 9) "0$hora" else "$hora"}:${if (minuto <= 9) "0$minuto" else "$minuto"}"
        }, calendarioHora, calendarioMinuto, true
    )

    Scaffold(
        topBar = {
            CustomTopBar(titulo = "configurar alarma", permiteVolver = true) { navController.popBackStack() }
        },
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            LazyColumn(
                modifier = Modifier
                    .padding(15.dp)
            ) {
                item {
                    CustomTextField(
                        texto = nombreAlarma,
                        textoAyuda = "NOMBRE",
                        habilitado = true
                    ) {
                        nombreAlarma = it
                    }
                }
                item { Spacer(Modifier.padding(10.dp)) }

                item {
                    CustomTextField(
                        texto = descripcionAlarma,
                        textoAyuda = "DESCRIPCION",
                        habilitado = true
                    ) {
                        descripcionAlarma = it
                    }
                }
                item { Spacer(Modifier.padding(10.dp)) }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CustomCheckbox(texto = "REPETITIVA", activado = repetitivaActivado) {
                            repetitivaActivado = it
                        }
                        CustomCheckbox(texto = "UNICA", activado = !repetitivaActivado) {
                            repetitivaActivado = !it
                        }
                    }
                }
                item { Spacer(Modifier.padding(10.dp)) }

                if (repetitivaActivado) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            diasRepetitiva.forEachIndexed { indice, habilitada ->
                                Circle(indice, habilitada == 1) {
                                    val arrayTemp = diasRepetitiva.toMutableList()
                                    arrayTemp[indice] = if (habilitada == 1) 0 else 1
                                    diasRepetitiva = arrayTemp
                                }
                            }
                        }
                    }
                } else {
                    item {
                        CustomTextField(
                            texto = diaUnica,
                            textoAyuda = "DIA",
                            habilitado = false,
                            iconoTrasero = {
                                Icon(Icons.Default.DateRange, "")
                            },
                            accionClick = {
                                selectorFecha.show()
                            },
                            accionCambioDeValor = {}
                        )
                    }
                }
                item { Spacer(Modifier.padding(10.dp)) }

                item {
                    CustomTextField(
                        texto = horaAlarma,
                        textoAyuda = "HORA",
                        habilitado = false,
                        iconoTrasero = {
                            Icon(Icons.Default.Schedule, "")
                        },
                        accionClick = {
                            selectorHora.show()
                        },
                        accionCambioDeValor = { horaAlarma = it }
                    )
                }
            }

            Row(Modifier.weight(1f, false).padding(bottom = 15.dp).padding(horizontal = 15.dp)){
                CustomButton("guardar") {
                    val alarmaTemp =
                        if (repetitivaActivado)
                            AlarmaRepetitiva(
                                id = null,
                                nombre = nombreAlarma,
                                descripcion = descripcionAlarma,
                                fechaCreacion = Date(),
                                dias = diasRepetitiva,
                                hora = horaAlarma,
                            )
                        else
                            AlarmaUnica(
                                id = null,
                                nombre = nombreAlarma,
                                descripcion = descripcionAlarma,
                                fechaCreacion = Date(),
                                fecha = stringToDate(diaUnica, horaAlarma)
                            )
                    viewModel.agregarAlarma(alarmaTemp)
                    Toast.makeText(context, "Alarma agregada", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
            }
        }
    }
}

private fun stringToDate(dia: String, hora: String): Date {
    val diaSeparado = dia.split("/").map{ it.toInt() }
    val horaSeparada = hora.split(":").map{ it.toInt() }
    val localDateTime = LocalDateTime.of(diaSeparado[2], diaSeparado[1], diaSeparado[0], horaSeparada[0], horaSeparada[1])
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
}