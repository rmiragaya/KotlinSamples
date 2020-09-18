package com.rodrigomiragaya.kotlinexamplessamples.saveStateHandle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle

/**
 * Instead of implementing Viewmodel() we implement the custom StateViewmodel so we can use the state
 * variable to save and retrieve values, even if the process is killed
 * */
class SaveStateActivityViewModel (state : SavedStateHandle) : StateViewModel(state) {

    // Variable to save an object
    val name = MutableLiveData<String?>()

    // use BindData to save a few lines on each variable
    init {
        bindData("NAME", name)
    }
}