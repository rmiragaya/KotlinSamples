package com.rodrigomiragaya.kotlinexamplessamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rodrigomiragaya.kotlinexamplessamples.recyclerViewGet.RecyclerViewActivity
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.RetrofitGetActivity
import com.rodrigomiragaya.kotlinexamplessamples.retrofitPost.RetrofitPostActivity
import com.rodrigomiragaya.kotlinexamplessamples.saveStateHandle.SaveStateActivity
import kotlinx.android.synthetic.main.activity_main.*

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveStateBtn.setOnClickListener {
            val intent = Intent(this, SaveStateActivity::class.java)
            startActivity(intent)
        }

        retrofitGetBtn.setOnClickListener {
            val intent = Intent(this, RetrofitGetActivity::class.java)
            startActivity(intent)
        }

        recyvlerBtn.setOnClickListener {
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }

        retrofitPostBtn.setOnClickListener {
            val intent = Intent(this, RetrofitPostActivity::class.java)
            startActivity(intent)
        }

    }
}