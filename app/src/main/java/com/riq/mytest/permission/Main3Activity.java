package com.riq.mytest.permission;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.riq.mylibrary.utils.Lcat;
import com.riq.mylibrary.utils.ToastUtils;
import com.riq.mytest.R;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;


/**
 * MPermissions
 * 封装权限请求, 鸿洋
 */
public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }

    public void request(View view) {
        MPermissions.requestPermissions(this, 0x1, Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.GET_ACCOUNTS
                , Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(0x1)
    public void requestPermissionSuccess() {
        ToastUtils.showToast(this, "请求成功");
        Lcat.print("请求成功");

    }

    @PermissionDenied(0x1)
    public void requestPermissionFailed() {
        ToastUtils.showToast(this, "请求失败");
        Lcat.print("请求失败");
    }


}
