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

import static android.Manifest.permission.READ_PHONE_STATE;


/**
 * MPermissions
 * 封装权限请求, 鸿洋
 */
public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        MPermissions.requestPermissions(this, 0x1, READ_PHONE_STATE);
        int i = 0;
        Lcat.print(++i);
        int b = 0;
        Lcat.print(b++);
        int c = 0;
        c = c + 1;
        Lcat.print(c);
    }

    public void requestStorage(View view) {
        MPermissions.requestPermissions(this, 0x2, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(0x1)
    public void requestStorageSuccess() {
        ToastUtils.showToast(this, "请求内存成功");
        Lcat.print("请求内存成功");

    }

    @PermissionDenied(0x1)
    public void requestStorageFailed() {
        ToastUtils.showToast(this, "请求内存失败");
        Lcat.print("请求内存失败");
    }

    @PermissionGrant(0x2)
    public void requestpSuccess() {
        ToastUtils.showToast(this, "请求电话成功");
        Lcat.print("请求电话成功");


    }

    @PermissionDenied(0x2)
    public void requestpFailed() {
        ToastUtils.showToast(this, "请求电话失败");
        Lcat.print("请求电话失败");
    }


}
