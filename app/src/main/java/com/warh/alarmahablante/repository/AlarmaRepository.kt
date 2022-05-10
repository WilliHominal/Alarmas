package com.warh.alarmahablante.repository

import com.warh.alarmahablante.dao.AlarmaDao
import com.warh.alarmahablante.model.Alarma
import com.warh.alarmahablante.model.AlarmaRepetitiva
import com.warh.alarmahablante.model.AlarmaUnica
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class AlarmaRepository(private val alarmaDao: AlarmaDao) {

    val alarmasRepetitivas: Flow<List<AlarmaRepetitiva>> = alarmaDao.obtenerAlarmasRepetitivas()
    val alarmasUnicas: Flow<List<AlarmaUnica>> = alarmaDao.obtenerAlarmasUnicas()

    suspend fun agregarAlarmaRepetitiva(alarma: AlarmaRepetitiva) = alarmaDao.agregarAlarmaRepetitiva(alarma)
    suspend fun borrarAlarmaRepetitiva(alarma: AlarmaRepetitiva) = alarmaDao.borrarAlarmaRepetitiva(alarma)

    suspend fun agregarAlarmaUnica(alarma: AlarmaUnica) = alarmaDao.agregarAlarmaUnica(alarma)
    suspend fun borrarAlarmaUnica(alarma: AlarmaUnica) = alarmaDao.borrarAlarmaUnica(alarma)
}