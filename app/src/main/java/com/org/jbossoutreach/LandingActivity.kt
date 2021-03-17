package com.org.jbossoutreach

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.org.jbossoutreach.Managers.PermissionManager

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val i = Intent(this@LandingActivity, MainActivity::class.java)
        startActivity(i)
        val permissionManager = PermissionManager(this, this)
        if (!permissionManager.permissionStatus(Manifest.permission.INTERNET)) {
            permissionManager.requestPermission(256, Manifest.permission.INTERNET)
        }
        if (!permissionManager.permissionStatus(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permissionManager.requestPermission(259, Manifest.permission.INTERNET)
        }
    }
}