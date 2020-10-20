package com.rodrigomiragaya.kotlinexamplessamples.coil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import coil.size.Scale
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.rodrigomiragaya.kotlinexamplessamples.R
import kotlinx.android.synthetic.main.activity_coil.*

class CoilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coil)

        imageView1.load("https://s3-us-west-2.amazonaws.com/uw-s3-cdn/wp-content/uploads/sites/6/2017/11/04133712/waterfall-750x500.jpg"){
            transformations(RoundedCornersTransformation(30f))
            placeholder(R.drawable.ic_place_holder)
            scale(Scale.FILL)
        }

//        imageView2.load(R.drawable.coil_image_2){
        imageView2.load("https://s3-us-west-2.amazonaws.com/uw-s3-cdn/wp-content/uploads/sites/6/2017/11/04133712/waterfall-750x500.jpg"){
            transformations(RoundedCornersTransformation(30f))
            scale(Scale.FILL)
        }

        imageView3.load("src/main/res/drawable/coil_image_3.jpg"){
            crossfade(true)
            crossfade(1000)
            error(R.drawable.ic_place_holder)
        }
    }
}