package com.warh.alarmahablante.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

abstract class Alarma {
    abstract val id: Int?
    abstract var nombre: String
    abstract var descripcion: String
    abstract val fechaCreacion: Date
}

@Entity(tableName = "alarma_repetitiva")
data class AlarmaRepetitiva(
    @PrimaryKey(autoGenerate = true)
    override val id: Int?,
    override var nombre: String,
    override var descripcion: String,
    override var fechaCreacion: Date,
    var dias: List<Int>,
    var hora: String,
): Alarma()

@Entity(tableName = "alarma_unica")
data class AlarmaUnica(
    @PrimaryKey(autoGenerate = true)
    override val id: Int?,
    override var nombre: String,
    override var descripcion: String,
    override var fechaCreacion: Date,
    var fecha: Date
): Alarma()