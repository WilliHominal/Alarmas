package com.warh.alarmahablante.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.warh.alarmahablante.model.AlarmaRepetitiva
import com.warh.alarmahablante.model.AlarmaUnica

@Database(entities = [AlarmaRepetitiva::class, AlarmaUnica::class], version = 3)
@TypeConverters(Converters::class)
abstract class AlarmaDatabase: RoomDatabase() {
    abstract fun AlarmaDao(): AlarmaDao

    companion object {
        private var INSTANCIA: AlarmaDatabase? = null

        fun getDatabase(context: Context): AlarmaDatabase {
            return INSTANCIA ?: synchronized(this) {
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    AlarmaDatabase::class.java,
                    "alarma_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCIA = instancia
                instancia
            }
        }
    }
}