package com.warh.alarmahablante.dao

import androidx.room.*
import com.warh.alarmahablante.model.AlarmaRepetitiva
import com.warh.alarmahablante.model.AlarmaUnica
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarAlarmaRepetitiva(alarma: AlarmaRepetitiva)

    @Delete
    suspend fun borrarAlarmaRepetitiva(alarma: AlarmaRepetitiva)

    @Query("SELECT * FROM alarma_repetitiva")
    fun obtenerAlarmasRepetitivas(): Flow<List<AlarmaRepetitiva>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarAlarmaUnica(alarma: AlarmaUnica)

    @Delete
    suspend fun borrarAlarmaUnica(alarma: AlarmaUnica)

    @Query("SELECT * FROM alarma_unica")
    fun obtenerAlarmasUnicas(): Flow<List<AlarmaUnica>>
}