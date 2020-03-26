package com.bawei.bwonlineshopping.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bawei.bwonlineshopping.R;

public class CatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch2);
        Object object=null;
        object.toString();
//        requestPermissions();
    }

//    private void requestPermissions() {
////        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
////            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
////
////        }else {
////            Log.w("wgh","run here");
////            Object object=null;
////            object.toString();
////        }
////    }
////
////    @Override
////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
////        switch (requestCode){
////            case 100:
////                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
////                    Object object = null;
////                    object.toString();
////                }else {
////
////                }
////               return;
////        }
////    }
}
