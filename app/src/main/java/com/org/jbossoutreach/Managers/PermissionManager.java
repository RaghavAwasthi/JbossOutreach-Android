package com.org.jbossoutreach.Managers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.widget.Toast;



public class PermissionManager implements ActivityCompat.OnRequestPermissionsResultCallback{

    private Context context;
    private Activity activity;

    public PermissionManager(Context context,Activity activity){

        this.context = context;
        this.activity = activity;
    }
    public boolean permissionStatus(String permission){
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission(int constantInt,String permission){
        ActivityCompat.requestPermissions(activity,
                new String[]{permission},
                constantInt);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch(requestCode){
            case 512:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(context, "Permission Granted!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "No Permission Granted!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

}