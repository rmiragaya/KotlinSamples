package com.rodrigomiragaya.kotlinexamplessamples.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rodrigomiragaya.kotlinexamplessamples.R
import com.rodrigomiragaya.kotlinexamplessamples.saveStateHandle.StateViewModel
import kotlinx.android.synthetic.main.activity_retrofit_get.*

const val TAG = "RetrofitGetActivity"

class RetrofitGetActivity : AppCompatActivity() {

    private lateinit var viewModel : RetrofitActivityViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_get)

        // Init VModel with StateViewModel.Factory
        viewModel = ViewModelProvider(this, StateViewModel.Factory(this, savedInstanceState)).get(RetrofitActivityViewmodel::class.java)

        getPostBtn.setOnClickListener {  viewModel.getPost() }
        getErrorPostBtn.setOnClickListener { viewModel.getErrorPost() }
        getPostNumberBtn.setOnClickListener { viewModel.getPostNumber(intFromEditTExt()) }
        getUserIdPostBtn.setOnClickListener { viewModel.getUserIdPosts(intFromEditTExt()) }

        initObservers()

    }

    private fun intFromEditTExt() : Int {
        return try {
            postNumberEditText.text.toString().toInt()
        } catch (e :Exception){
            0
        }
    }
    private fun initObservers(){
        viewModel.getPostResponse.observe(this, Observer { response ->
            viewModel.post.value = response
        })

        viewModel.post.observe(this, Observer { posts ->
            posts?.let {
                if (it.errorResponse != null) {
                    postResponseTV.text = "Codigo: ${it.errorResponse!!.code} Description: ${it.errorResponse!!.description}"
                } else {
                    var postString = ""
                    it.value!!.forEach {
                        postString += it.toString() + "\n\n"
                    }
                    postResponseTV.text = postString
                }
            }
        })
    }
}