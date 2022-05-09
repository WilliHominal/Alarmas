package com.warh.alarmahablante.viewmodel

import android.app.Application
import com.warh.alarmahablante.dao.AlarmaDatabase
import com.warh.alarmahablante.repository.AlarmaRepository

class AlarmaApplication: Application() {
    private val database by lazy { AlarmaDatabase.getDatabase(this) }
    val repository by lazy { AlarmaRepository(database.AlarmaDao()) }
}