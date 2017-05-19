package com.riq.mytest;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main5Activity extends AppCompatActivity {

    @BindView(R.id.btn)
    Button btn;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ButterKnife.bind(this);
        mediaPlayer = MediaPlayer.create(this, R.raw.app_launcher);
//        Utils.SoundPlayHelper.getInstance().init(this, new int[]{R.raw.beep, R.raw.read_id_success, R.raw.app_launcher});
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
//        Utils.SoundPlayHelper.getInstance().playSound(this, R.raw.read_id_success, 1, -1, 1);
        try {
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mediaPlayer.isPlaying() && mediaPlayer != null) {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
    }
}
