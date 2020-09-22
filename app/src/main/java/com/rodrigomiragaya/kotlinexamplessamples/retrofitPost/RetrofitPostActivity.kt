package com.rodrigomiragaya.kotlinexamplessamples.retrofitPost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rodrigomiragaya.kotlinexamplessamples.R
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.Post
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.RetrofitActivityViewmodel
import com.rodrigomiragaya.kotlinexamplessamples.saveStateHandle.StateViewModel
import kotlinx.android.synthetic.main.activity_retrofit_get.*
import kotlinx.android.synthetic.main.activity_retrofit_post.*

class RetrofitPostActivity : AppCompatActivity() {

    private lateinit var viewModel : RetrofitPostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_post)

        // Init VModel with StateViewModel.Factory
        viewModel = ViewModelProvider(this, StateViewModel.Factory(this, savedInstanceState)).get(RetrofitPostViewModel::class.java)

        postBtn.setOnClickListener { createSendPost() }
        initObservers()


    }

    private fun initObservers(){
        viewModel.getPostResponse.observe(this, Observer { response ->
            viewModel.post.value = response
        })

        viewModel.post.observe(this, Observer { posts ->
            posts?.let {
                if (it.errorResponse != null) {
                    Toast.makeText(
                        this,
                        it.errorResponse!!.description.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        "Post OK",
                        Toast.LENGTH_SHORT
                    ).show()
                    responsePostTxt.text = it.value!![0].toString()
                }
            }
        })
    }

    private fun createSendPost(){
        val newPost = Post(userIdEdtxt.text.toString().toInt(),2, titleEdtxt.text.toString(), bodyEdtxt.text.toString())
        viewModel.pushPost(newPost)
    }
}