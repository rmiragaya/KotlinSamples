package com.rodrigomiragaya.kotlinexamplessamples.recyclerViewGet

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodrigomiragaya.kotlinexamplessamples.R
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.Post
import com.rodrigomiragaya.kotlinexamplessamples.saveStateHandle.StateViewModel
import kotlinx.android.synthetic.main.activity_recycler_view.*
import java.util.*

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var viewModel : RecyclerViewViewModel
    private val adapter by lazy { MyAdapter { item -> doClick(item) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        // Init VModel with StateViewModel.Factory
        viewModel = ViewModelProvider(this, StateViewModel.Factory(this, savedInstanceState)).get(
            RecyclerViewViewModel::class.java
        )

        getPostsBtn.setOnClickListener { viewModel.getUserIdPosts(getRandomUserID()) }

        initRecycler()
        initObservers()

    }

    private fun initRecycler(){
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun initObservers(){
        viewModel.getPostResponse.observe(this, Observer { response ->
            viewModel.setPost(response)
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
                    adapter.setData(it.value!!)
                }
            }
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading?.let {
                if (isLoading) loadingId.visibility = View.VISIBLE else loadingId.visibility = View.INVISIBLE
            }
        })
    }

    private fun getRandomUserID() : Int{
        val randomUserID = Random().nextInt(5 - 0 + 1) + 0
        Toast.makeText(this, "$randomUserID", Toast.LENGTH_SHORT).show()
        return randomUserID
    }

    private fun doClick(post: Post){
        Toast.makeText(this, post.toString(), Toast.LENGTH_SHORT).show()
    }
}