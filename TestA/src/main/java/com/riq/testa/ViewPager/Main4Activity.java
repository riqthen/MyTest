package com.riq.testa.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.riq.testa.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main4Activity extends AppCompatActivity {

    @BindView(R.id.vp)
    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);

        List<String> images = new ArrayList<>();
        images.add("http://tvax4.sinaimg.cn/crop.24.0.1194.1194.180/7b469293ly8fdl6s61zypj20yi0x6767.jpg");
        images.add("/storage/emulated/0/2628.jpg");
        images.add("/storage/emulated/0/benke2/_cache/17691432778945.jpg_480*480");
        images.add("http://wx1.sinaimg.cn/mw690/6d38ee54gy1fgl5tl49y1j20k00mhju0.jpg");

        List<String> titles = new ArrayList<>();
        titles.add("111");
        titles.add("222");
        titles.add("333");
        titles.add("555");


        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);  //设置banner样式
        banner.setImageLoader(new GlideImageLoader());  //设置图片加载器
        banner.setImages(images);   //设置图片集合
        banner.setBannerAnimation(Transformer.DepthPage);   //设置banner动画效果
        banner.setBannerTitles(titles); //设置标题集合（当banner样式有显示title时）
        banner.isAutoPlay(true);    //是否轮播
        banner.setDelayTime(1500);  //设置轮播时间




        banner.start();
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);

        }

    }
}
