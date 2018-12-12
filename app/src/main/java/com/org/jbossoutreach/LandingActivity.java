package com.org.jbossoutreach;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.org.jbossoutreach.Managers.PermissionManager;

import java.util.Arrays;

public class LandingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Intent i = new Intent(LandingActivity.this, MainActivity.class);
        startActivity(i);
        PermissionManager permissionManager = new PermissionManager(this, this);
        if (!permissionManager.permissionStatus(Manifest.permission.INTERNET)) {
            permissionManager.requestPermission(256, Manifest.permission.INTERNET);
        }
        if (!permissionManager.permissionStatus(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permissionManager.requestPermission(259, Manifest.permission.INTERNET);
        }


    }


}
