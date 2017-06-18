package com.riq.testa;

import android.app.Application;

import com.flowerfat.volleyutil.main.VolleyUtils;

/**
 * Created by 锐 on 2017/6/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyUtils.getInstance().init(this);
    }
}
