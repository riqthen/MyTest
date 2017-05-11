package com.riq.mylibrary.base;

import android.app.Activity;
import android.app.Application;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 10903 on 2017/4/27.
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    // TODO: 2017/4/27 循环遍历退出
    private static List<Activity> mList = new LinkedList<>();

    public static void addActivity(Activity activity) {
        mList.add(activity);
    }

    public static void exitApp() {
        try {
            for (Activity activity : mList) {
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    // TODO: 2017/4/27 双击退出
    long time;

    public void twiceToExit() {
        if (System.currentTimeMillis() - time < 2000) {
            exitApp();
        } else {
            time = System.currentTimeMillis();
            Toast.makeText(instance, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
    }

}
