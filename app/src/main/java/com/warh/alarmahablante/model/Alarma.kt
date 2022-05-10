package com.warh.alarmahablante.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

abstract class Alarma {
    abstract val id: Int?
    abstract var nombre: String
    abstract var descripcion: String
    abstract val fechaCreacion: Date
    abstract var habilitada: Boolean
}

@Entity(tableName = "alarma_repetitiva")
data class AlarmaRepetitiva(
    @PrimaryKey(autoGenerate = true)
    override val id: Int?,
    override var nombre: String,
    override var descripcion: String,
    override var fechaCreacion: Date,
    override var habilitada: Boolean,
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
    override var habilitada: Boolean,
    var fecha: Date
): Alarma()