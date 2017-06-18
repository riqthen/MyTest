package com.riq.testa.life;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.riq.mylibrary.utils.Lcat;
import com.riq.testa.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Lcat.print("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Lcat.print("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Lcat.print("onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Lcat.print("onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lcat.print("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lcat.print("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lcat.print("onDestroy");
    }

    @OnClick(R.id.tv)
    public void onViewClicked() {
        startActivity(new Intent(this, Main2Activity.class));
    }
}
