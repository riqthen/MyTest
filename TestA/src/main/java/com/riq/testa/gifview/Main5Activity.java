package com.riq.testa.gifview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.riq.testa.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main5Activity extends AppCompatActivity {

//    @BindView(R.id.gv)
//    GifView gv;
    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ButterKnife.bind(this);
        Glide.with(this)
                .load("http://wx3.sinaimg.cn/mw690/6927e7a5ly1fgkqy439xxg20780424it.gif")
//                .load("http://img.my.csdn.net/uploads/201705/03/1493822964_4188.jpg")
//                .asGif()
                .into(iv);

//        gv.play();
//        gv.pause();
//        gv.setGifResource(R.mipmap.qq);
//        gv.getGifResource();
//        gv.setMovieTime(time);
//        gv.getMovie();

    }

}
