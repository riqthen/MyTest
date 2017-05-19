package com.riq.mytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.riq.mylibrary.utils.Lcat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.riq.mylibrary.utils.DateUtils.formatTimestamp;

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
        Lcat.print(formatTimestamp(s));
        Lcat.print(formatTimestamp(System.currentTimeMillis() + ""));
        Lcat.print(formatTimestamp("2015年5月2日 22:55:22.0"));


    }



}
