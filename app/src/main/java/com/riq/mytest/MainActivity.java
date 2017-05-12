package com.riq.mytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.riq.mylibrary.utils.Lcat;
import com.riq.mylibrary.utils.RandomUtils;
import com.riq.mylibrary.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.tv, R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv:
                break;
            case R.id.btn:
                Lcat.print(RandomUtils.shuffle(new Object[]{1, "yy", "你", 565, 33}, 3));
                Lcat.print(RandomUtils.shuffle(new Object[]{1, "yy", "你", 565, 33}));
                Lcat.print(RandomUtils.shuffle(new int[]{1, 3, 2, 565, 33}, true));
                Lcat.print(RandomUtils.shuffle(new int[]{1, 3, 2, 565, 33}, 4));
                Lcat.print(StringUtils.isNumber("184651561515"));
                break;
        }
    }


}
