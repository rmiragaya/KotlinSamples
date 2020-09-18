package com.rodrigomiragaya.kotlinexamplessamples.saveStateHandle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rodrigomiragaya.kotlinexamplessamples.R
import kotlinx.android.synthetic.main.activity_save_state.*

/**
 * Example of SaveStateHandle:
 * This is a key-value map that will let you write and retrieve objects to and from the saved state.
 * These values will persist after the process is killed by the system and remain available
 * via the same object.
 * */
class SaveStateActivity : AppCompatActivity() {

    // LateInit VModel
    private lateinit var vmodel : SaveStateActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_state)

        // Init VModel with StateViewModel.Factory
        vmodel = ViewModelProvider(this, StateViewModel.Factory(this, savedInstanceState)).get(SaveStateActivityViewModel::class.java)

        savebtn.setOnClickListener {
            if (!validate())return@setOnClickListener
            vmodel.name.value = (nameEditText.text.toString())
        }

        initObservers()
    }

    private fun initObservers(){
        //Observe changes in name MutableLiveData
        vmodel.name.observe(this, Observer{
            it?.let {
                holaId.text = it
                savedNameVMId.text = "Saved in ViewModel: $it"
            }
        })
    }


    private fun validate(): Boolean{
        return if (nameEditText.text.toString().isNotBlank()) true else {
            nameEditText.error = "Campo Obligatorio"
            false
        }
    }
}