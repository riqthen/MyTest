package com.riq.mytest;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.riq.mylibrary.utils.ToastUtils;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.riq.mylibrary.utils.ContactHelper.getContact;
import static com.riq.mylibrary.utils.ContactHelper.setContactToView;


public class Main4Activity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        MPermissions.requestPermissions(this, 0x1, Manifest.permission.READ_CONTACTS);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0x100:
                setContactToView(this, data, etName);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @PermissionDenied(0x1)
    public void noContactPermission() {
        ToastUtils.showToast(this, "没有权限");
    }

    @PermissionGrant(0x1)
    public void getPermission() {
        getContact(this, 0x100);
    }

}
