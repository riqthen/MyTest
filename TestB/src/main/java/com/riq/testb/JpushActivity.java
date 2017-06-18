package com.riq.testb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

public class JpushActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpush);
        butterknife.ButterKnife.bind(this);
    }

    @OnClick(R.id.tv)
    public void onViewClicked() {

    }
}
