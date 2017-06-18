package com.riq.testb;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 锐 on 2017/6/17.
 */

public class JpushApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}