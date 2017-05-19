package com.riq.mytest.zxingLibrary;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.riq.mytest.R;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main6Activity extends AppCompatActivity {


    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn_scan, R.id.btn_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_scan:
                Intent intent = new Intent(Main6Activity.this, Main7Activity.class);
                startActivityForResult(intent, 0x1);
                break;
            case R.id.btn_img:
                Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
                intent2.addCategory(Intent.CATEGORY_OPENABLE);
                intent2.setType("image/*");
                startActivityForResult(intent2, 0x2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 0x1:
                //处理扫描结果（在界面上显示）
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        tv.setText(result);
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        Toast.makeText(Main6Activity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case 0x2:
                if (data != null) {
                    Uri uri = data.getData();
                    ContentResolver cr = getContentResolver();
                    try {
                        Bitmap mBitmap = MediaStore.Images.Media.getBitmap(cr, uri);//显得到bitmap图片
                        CodeUtils.analyzeBitmap(String.valueOf(mBitmap), new CodeUtils.AnalyzeCallback() {
                            @Override
                            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                                Toast.makeText(Main6Activity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onAnalyzeFailed() {
                                Toast.makeText(Main6Activity.this, "读取失败", Toast.LENGTH_LONG).show();
                            }
                        });

                        if (mBitmap != null) {
                            mBitmap.recycle();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }
}
