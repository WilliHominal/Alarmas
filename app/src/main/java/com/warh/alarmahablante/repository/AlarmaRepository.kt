package com.warh.alarmahablante.repository

import com.warh.alarmahablante.dao.AlarmaDao
import com.warh.alarmahablante.model.Alarma
import com.warh.alarmahablante.model.AlarmaRepetitiva
import com.warh.alarmahablante.model.AlarmaUnica
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class AlarmaRepository(private val alarmaDao: AlarmaDao) {
    suspend fun agregarAlarmaRepetitiva(alarma: AlarmaRepetitiva) = alarmaDao.agregarAlarmaRepetitiva(alarma)
    suspend fun borrarAlarmaRepetitiva(alarma: AlarmaRepetitiva) = alarmaDao.borrarAlarmaRepetitiva(alarma)
    suspend fun obtenerAlarmas(): List<Alarma> {
        val alarmas = mutableListOf<Alarma>()
        alarmas.addAll(alarmaDao.obtenerAlarmasRepetitivas())
        alarmas.addAll(alarmaDao.obtenerAlarmasUnicas())
        return alarmas
    }

    suspend fun agregarAlarmaUnica(alarma: AlarmaUnica) = alarmaDao.agregarAlarmaUnica(alarma)
    suspend fun borrarAlarmaUnica(alarma: AlarmaUnica) = alarmaDao.borrarAlarmaUnica(alarma)
}