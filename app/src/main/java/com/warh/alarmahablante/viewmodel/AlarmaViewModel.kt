package com.warh.alarmahablante.viewmodel

import androidx.lifecycle.*
import com.warh.alarmahablante.model.Alarma
import com.warh.alarmahablante.model.AlarmaRepetitiva
import com.warh.alarmahablante.model.AlarmaUnica
import com.warh.alarmahablante.repository.AlarmaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AlarmaViewModel(private val alarmaRepository : AlarmaRepository): ViewModel() {
    val listaAlarmasRepetitivas: LiveData<List<AlarmaRepetitiva>> = alarmaRepository.alarmasRepetitivas.asLiveData()
    val listaAlarmasUnicas: LiveData<List<AlarmaUnica>> = alarmaRepository.alarmasUnicas.asLiveData()

    fun agregarAlarma(alarma: Alarma){
        viewModelScope.launch(Dispatchers.IO) {
            when (alarma){
                is AlarmaRepetitiva -> alarmaRepository.agregarAlarmaRepetitiva(alarma)
                is AlarmaUnica -> alarmaRepository.agregarAlarmaUnica(alarma)
            }
        }
    }
}

class AlarmaViewModelFactory(private val repository: AlarmaRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlarmaViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return AlarmaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}