package com.org.jbossoutreach.Managers

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.core.content.ContextCompat

class PermissionManager(private val context: Context, private val activity: Activity) : OnRequestPermissionsResultCallback {
    fun permissionStatus(permission: String?): Boolean {
        return ContextCompat.checkSelfPermission(context, permission!!) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(constantInt: Int, permission: String) {
        ActivityCompat.requestPermissions(activity, arrayOf(permission),
                constantInt)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            512 -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "Permission Granted!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "No Permission Granted!", Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
    }
}