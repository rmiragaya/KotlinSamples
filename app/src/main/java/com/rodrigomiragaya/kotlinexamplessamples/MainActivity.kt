package com.rodrigomiragaya.kotlinexamplessamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rodrigomiragaya.kotlinexamplessamples.saveStateHandle.SaveStateActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveStateBtn.setOnClickListener {
            val intent = Intent(this, SaveStateActivity::class.java)
            startActivity(intent)
        }

    }
}