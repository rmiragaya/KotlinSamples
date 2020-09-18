package com.rodrigomiragaya.kotlinexamplessamples.saveStateHandle

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

/**
 *
 * StateViewModel que tiene el metodo BindData para ahorrarnos
 * el hecho de setear en el state cada elemento que queremos guardar.
 * Tambien se le puede agregar un objeto al constructor del factory
 * y pasarlo a todos los VMs. (opcional)
 * */
abstract class StateViewModel (val state: SavedStateHandle) : ViewModel() {

    class Factory(private val owner: SavedStateRegistryOwner, args: Bundle?) : AbstractSavedStateViewModelFactory(owner, args) {
        override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
            return modelClass.getConstructor(SavedStateHandle::class.java).newInstance(handle)
        }
    }

    /** Ahorra estas lineas en cada VM */
    protected fun <T> bindData(key: String, data: MutableLiveData<T>): T? {
        data.value = state[key]
        data.observeForever { state.set(key, it) }
        return state[key]
    }

}