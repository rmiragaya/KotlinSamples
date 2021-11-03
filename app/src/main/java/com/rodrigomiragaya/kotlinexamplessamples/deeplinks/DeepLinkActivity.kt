package com.rodrigomiragaya.kotlinexamplessamples.deeplinks

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.shortLinkAsync
import com.google.firebase.ktx.Firebase
import com.rodrigomiragaya.kotlinexamplessamples.R
import kotlinx.android.synthetic.main.activity_deep_link.*

const val TAG = "DeepLinkActivity"
class DeepLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deep_link)

//        checkDynamicsLinks()
        crearDLinkBtn.setOnClickListener {
            createDynamicLink()
        }

    }

    private fun createDynamicLink() {
        val shortLinkTask = Firebase.dynamicLinks.shortLinkAsync {
            link = Uri.parse("https://www.example.com/?monto=123&cuenta=858&usuario=Deysi")
            domainUriPrefix = "https://rmiragayaotlinsamples.page.link"
            setAndroidParameters(DynamicLink.AndroidParameters.Builder().build())
            // Set parameters
            // ...
        }.addOnSuccessListener { shortLink ->
            // You'll need to import com.google.firebase.dynamiclinks.ktx.component1 and
            // com.google.firebase.dynamiclinks.ktx.component2
            // Short link created
            Log.d(TAG, "createDynamicLink Succes")
            Log.d(TAG, "shortLink.shortLink ${shortLink.shortLink}")
            Log.d(TAG, "shortLink.previewLink ${shortLink.previewLink}")
            Log.d(TAG, "shortLink.warnings ${shortLink.warnings}")
        }.addOnFailureListener {
            // Error
            Log.d(TAG, "addOnFailureListener: call, ${it.printStackTrace()}")
        }
    }

    override fun onStart() {
        super.onStart()
        checkDynamicsLinks()
    }

    private fun checkDynamicsLinks() {
//        FirebaseDynamicLinks.getInstance().getDynamicLink(intent)
//            .addOnSuccessListener(this) { pendingDynamicLinkData ->
//                //succes
//                Log.i(TAG, "gpLink succes")
//                // Get deep link from result (may be null if no link is found)
//                var deepLink: Uri? = null
//                if (pendingDynamicLinkData != null) {
//                    deepLink = pendingDynamicLinkData.link
//                }
//                if (deepLink != null) {
//                    Log.i(TAG, "deeplin = ${deepLink.toString()} ")
//                    var monto = deepLink.getQueryParameter("monto")?.toInt()
//                    var cuenta = deepLink.getQueryParameter("monto")?.toInt()
//                    var usuario = deepLink.getQueryParameter("monto")
//                    Log.i(TAG, "monto: $monto")
//                    Log.i(TAG, "cuenta: $cuenta")
//                    Log.i(TAG, "usuario: $usuario")
//                }
//            }.addOnFailureListener(this) {
//                //failure
//                Log.i(TAG, "gpLink fail")
//            }

        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                // Get deep link from result (may be null if no link is found)
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link
                }
                if (deepLink != null) {
                    Log.i(TAG, "deeplin = ${deepLink.toString()} ")
                    var monto = deepLink.getQueryParameter("monto")?.toInt()
                    var cuenta = deepLink.getQueryParameter("monto")?.toInt()
                    var usuario = deepLink.getQueryParameter("monto")
                    Log.i(TAG, "monto: $monto")
                    Log.i(TAG, "cuenta: $cuenta")
                    Log.i(TAG, "usuario: $usuario")
                }

            }
            .addOnFailureListener(this) { e -> Log.w(TAG, "getDynamicLink:onFailure", e) }
    }
}