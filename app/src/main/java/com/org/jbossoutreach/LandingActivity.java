package com.org.jbossoutreach;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.org.jbossoutreach.Managers.PermissionManager;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        PermissionManager permissionManager =new PermissionManager(this,this);
        if (!permissionManager.permissionStatus(Manifest.permission.INTERNET)) {
            permissionManager.requestPermission(256, Manifest.permission.INTERNET);
        }
        Intent i =new Intent(LandingActivity.this,RepositoryActivity.class);
        startActivity(i);
    }
}
