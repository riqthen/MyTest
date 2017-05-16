package com.riq.mytest.permission;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.riq.mylibrary.utils.FileUtils;
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

    public void requestStorage(View view) {
        MPermissions.requestPermissions(this, 0x1, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        RequestQueue queue3 = Volley.newRequestQueue(this);
        Request request3 = new ImageRequest("http://img06.tooopen.com/images/20161112/tooopen_sy_185726882764.jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                FileUtils.saveToStorage("/storage/emulated/0/", "fffs.png", response);
            }
        }, 800, 800, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Lcat.print("请求失败");
            }
        });
        queue3.add(request3);

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


}
