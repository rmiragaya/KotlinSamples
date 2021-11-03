package com.rodrigomiragaya.kotlinexamplessamples.permissions

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.rodrigomiragaya.kotlinexamplessamples.R
import kotlinx.android.synthetic.main.activity_permissions.*


class PermissionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        val cameraAndAudio = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )

        askCameraBtn.setOnClickListener {
            Dexter.withContext(this)
                .withPermissions(cameraAndAudio)
//                .withListener(this)
//                .check()
        }



//        val dialogPermissionListener: PermissionListener = DialogOnDeniedPermissionListener.Builder
//            .withContext(this)
//            .withTitle("Contactos")
//            .withMessage("Es necesario leer los contactos para continual")
//            .withButtonText(android.R.string.ok)
//            .withIcon(R.mipmap.ic_launcher)
//            .build()

        askContactsBtn.setOnClickListener {
            Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_CONTACTS)
                .withListener(getSinglePermissonListener())
                .check()
        }
    }

    private fun getSinglePermissonListener(): PermissionListener {
        return object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                // This method will be called when the permission is granted
                Log.d("PermissionsActivity", "onPermissionGranted call")

            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                // This method will be called when the permission is denied
                Log.d("PermissionsActivity", "onPermissionDenied call")
                openSettingsForPermission()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?,
                token: PermissionToken?
            ) {
                Log.d("PermissionsActivity", "onPermissionRationaleShouldBeShown call")
                // This method will be called when the user rejects a permission request
                // You must display a dialog box that explains to the user why the application needs this permission
                token?.continuePermissionRequest()
            }
        }

    }

    private fun openSettingsForPermission() {
            startActivity(Intent().apply {
                action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                data = Uri.fromParts("package", packageName, null)
            })
    }

//    override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
//        Toast.makeText(this, "Permisos otorgados", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onPermissionRationaleShouldBeShown(
//        p0: MutableList<PermissionRequest>?,
//        token: PermissionToken?
//    ) {
//        token?.continuePermissionRequest()
//        Toast.makeText(this, "Dialog que necesitamos el permiso", Toast.LENGTH_SHORT).show()
//    }
}