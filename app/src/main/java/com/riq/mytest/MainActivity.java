package com.riq.mytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.riq.mylibrary.utils.Lcat;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.et)
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        String s = et.getText().toString().trim();
        try {
            byte[] b_gbk = s.getBytes("GBK");
            byte[] b_utf8 = s.getBytes("UTF-8");
            byte[] b_iso88591 = s.getBytes("ISO8859-1");
            Lcat.print(b_gbk);
            Lcat.print(b_utf8);
            Lcat.print(b_iso88591);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



    }

}
