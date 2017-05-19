package com.riq.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.riq.mylibrary.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.riq.mylibrary.utils.Utils.ContactUtil.getContact;
import static com.riq.mylibrary.utils.Utils.ContactUtil.setContactToView;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Utils.SoundPlayUtils.getInstance().init(this, new int[]{R.raw.beep, R.raw.read_id_success, R.raw.app_launcher});
        Utils.SoundPlayUtils.getInstance().playSound(this, R.raw.app_launcher);

    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        getContact(this, 0x111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0x111:
                setContactToView(this, data, btn, et);
                break;
        }
    }
}
