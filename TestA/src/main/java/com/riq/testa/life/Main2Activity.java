package com.riq.testa.life;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.riq.mylibrary.utils.Lcat;
import com.riq.testa.R;

import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        Lcat.print("onCreate2");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Lcat.print("onStart2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Lcat.print("onResume2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Lcat.print("onRestart2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lcat.print("onPause2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lcat.print("onStop2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lcat.print("onDestroy2");
    }

    public void toA(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }
}
