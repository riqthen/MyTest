package com.riq.testa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.riq.mylibrary.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tv.setText("7777");

    }

    @OnClick(R.id.tv)
    public void onViewClicked() {
        ToastUtils.showToast(this, "点击");
    }

    @OnLongClick(R.id.tv)
    public boolean onLongClick() {
        ToastUtils.showToast(this, "长按");
        return true;
    }

}
