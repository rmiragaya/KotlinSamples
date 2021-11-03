package com.rodrigomiragaya.kotlinexamplessamples

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import com.rodrigomiragaya.kotlinexamplessamples.coil.CoilActivity
import com.rodrigomiragaya.kotlinexamplessamples.deeplinks.DeepLinkActivity
import com.rodrigomiragaya.kotlinexamplessamples.permissions.PermissionsActivity
import com.rodrigomiragaya.kotlinexamplessamples.recyclerViewGet.RecyclerViewActivity
import com.rodrigomiragaya.kotlinexamplessamples.retrofitGet.RetrofitGetActivity
import com.rodrigomiragaya.kotlinexamplessamples.retrofitPost.RetrofitPostActivity
import com.rodrigomiragaya.kotlinexamplessamples.saveStateHandle.SaveStateActivity
import kotlinx.android.synthetic.main.activity_main.*

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    //region $RegionExample

    fun estoEsGenaial(){
        //poner region y endregion hace un desplegable custom
    }

    //endregion


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

        coilBtn.setOnClickListener {
            val intent = Intent(this, CoilActivity::class.java)
            startActivity(intent)
        }

        deepLinkBtn.setOnClickListener {
            val intent = Intent(this, DeepLinkActivity::class.java)
            startActivity(intent)
        }

        permissionsBtn.setOnClickListener {
            val intent = Intent(this, PermissionsActivity::class.java)
            startActivity(intent)
        }
        checkDynamicsLinks()

        testCode()

    }



    override fun onStart() {
        super.onStart()
        checkDynamicsLinks()
    }

    private fun checkDynamicsLinks() {
        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                // Get deep link from result (may be null if no link is found)
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link
                }
                if (deepLink != null) {
                    Log.i(com.rodrigomiragaya.kotlinexamplessamples.deeplinks.TAG, "deeplink = ${deepLink.toString()} ")
                    var monto = deepLink.getQueryParameter("monto")?.toInt()
                    var cuenta = deepLink.getQueryParameter("cuenta")?.toInt()
                    var usuario = deepLink.getQueryParameter("usuario")
                    Log.i(com.rodrigomiragaya.kotlinexamplessamples.deeplinks.TAG, "monto: $monto")
                    Log.i(com.rodrigomiragaya.kotlinexamplessamples.deeplinks.TAG, "cuenta: $cuenta")
                    Log.i(com.rodrigomiragaya.kotlinexamplessamples.deeplinks.TAG, "usuario: $usuario")
                }

            }
            .addOnFailureListener(this) { e -> Log.w(com.rodrigomiragaya.kotlinexamplessamples.deeplinks.TAG, "getDynamicLink:onFailure", e) }
    }





    private fun testCode() {
//        getUniqueCharacter("casa")
//        getUniqueCharacter("qazwsxedcrfvtgbyhnvujkiolpsgeqa")
        println(countOccurrences("qazwsxedcrfvtgbyhnvujkiolpsgeqa", 'a'))
    }
    fun countOccurrences(s: String, ch: Char): Int {
        return s.filter { it == ch }.count()
    }


//    fun getUniqueCharacter(s : String): Int {
//
////        for (c in str) {
////            println(c)
////        }
//
//
//        for (i in 0..s.length-1) {
//            var count = 0
//            for (i in s) {
//                count++
//            }
//            if (count == 0) {
//                return i
//            }
//        }
//    }





}