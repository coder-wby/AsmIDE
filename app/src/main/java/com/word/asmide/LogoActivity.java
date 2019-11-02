package com.word.asmide;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class LogoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        initPermission();

    }

    private void initPermission() {
        if(!checkPermission()) {
            startRequestPermision(PermissionUtil.Permission);
        } else {
            toMainActivity();
            finish();
        }
    }

    //请求权限
    private void startRequestPermision(String[] permissions) {
        ActivityCompat.requestPermissions(this, permissions, PermissionUtil.REQUEST_CODE);
    }

    //处理权限申请回调
    @SuppressLint("InflateParams")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode ==  PermissionUtil.REQUEST_CODE&&grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //如果用户允许
            toMainActivity();
            finish();
        } else {
            //如果用户拒绝
            //说明申请权限原因
            AppCompatDialog dialog = new AppCompatDialog(this);
            View dialogView;
            dialogView = LayoutInflater.from(getApplication()).inflate(R.layout.permission_dialog,null);
            dialog.setContentView(dialogView);
            dialog.show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    //检查是否不存在需要申请的危险权限
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    private void toMainActivity() {
        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
    }

}
