package com.riq.mytest.permission;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.riq.mylibrary.utils.Lcat;
import com.riq.mylibrary.utils.ToastUtils;
import com.riq.mytest.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }


    private String[] permissions = {
            "Manifest.permission.WRITE_EXTERNAL_STORAGE"
            , "Manifest.permission.CAMERA"
            , "Manifest.permission.GET_ACCOUNTS"};

    public void request(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            for (int i = 0; i < 2; i++) {
//                if (checkSelfPermission(permissions[i]) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permissions, 0x1);
//                } else {
//                    ToastUtils.showToast(this, "6.0以上允许了权限");
//                    Lcat.print("6.0以上允许了权限");
//                }
//            }
        } else {//6.0以下
            ToastUtils.showToast(this, "6.0以下不需要动态权限");
            Lcat.print("6.0以下不需要动态权限");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Lcat.print(grantResults);
        switch (requestCode) {
            case 0x1:
                boolean hasGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean hasGranted1 = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                boolean hasGranted2 = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                if (hasGranted && hasGranted1 && hasGranted2) {
                    Lcat.print("已经赋予权限");
                } else {
                    Lcat.print("没有权限");
                }

        }
    }
}
