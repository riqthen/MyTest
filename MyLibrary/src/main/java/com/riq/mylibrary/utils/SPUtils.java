package com.riq.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Update by dreamriq on 2017/5/8.
 * 使用方法:
 * 在当前Activity中 SPUtils.getInstance().init(); 可以在该方法中设置sp的文件名
 * 储存获取的方法 SPUtils.getInstance().setSpString()  SPUtils.getInstance().getSpString()
 * 清除的方法 SPUtils.getInstance().clearSp()
 */
public class SPUtils {

    private static SPUtils spUtils;
    private Context mContext;
    private String spName;   //保存的文件名
    private String defaultSpName = "DEFAULT_NAME";   //默认文件名

    //初始化,用于获取当前context
    public void init(Context context) {
        this.mContext = context;
    }

    public void init(Context context, String spName) {
        this.mContext = context;
        this.spName = spName;
    }

    public static SPUtils getInstance() {
        if (spUtils == null) {
            spUtils = new SPUtils();
        }
        return spUtils;
    }

//    //设置文件名
//    public void setSpName(String spName) {
//        this.spName = spName;
//    }
//
//    public String getSpName() {
//        return spName == null ? defaultSpName : spName;
//    }

    // -------------> boolean
    public boolean setSpBoolean(String key, boolean value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName == null ? defaultSpName : spName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public boolean getSpBoolean(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName == null ? defaultSpName : spName, Activity.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, true);
    }

    public boolean getSpBoolean(String key, boolean defaultVal) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName == null ? defaultSpName : spName, Activity.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultVal);
    }

    // -------------> String
    public boolean setSpString(String key, String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName == null ? defaultSpName : spName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public String getSpString(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName == null ? defaultSpName : spName, Activity.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    // -------------> int
    public int getSpInt(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName == null ? defaultSpName : spName, Activity.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }

    public boolean setSpInt(String key, int value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName == null ? defaultSpName : spName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    // -------------> float
    public boolean setSpFloat(String key, float value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName == null ? defaultSpName : spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public double getSpFloat(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName == null ? defaultSpName : spName, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, 0);
    }

    // -------------> long
    public boolean setSpLong(String key, long value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName == null ? defaultSpName : spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public double getSpLong(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName == null ? defaultSpName : spName, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0);
    }

    // -------------> Set<String>
    public boolean setSpSet(String key, Set<String> value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName == null ? defaultSpName : spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, value);
        return editor.commit();
    }

    public Set<String> getSpSet(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName == null ? defaultSpName : spName, Context.MODE_PRIVATE);
        return sharedPreferences.getStringSet(key, new HashSet<String>());
    }


    //清除某个sp
    public boolean clearSp(String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName == null ? defaultSpName : spName, Activity.MODE_PRIVATE);
        return sharedPreferences.edit().clear().commit();
    }

    public boolean clearSp() { //清除默认sp
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(defaultSpName, Activity.MODE_PRIVATE);
        return sharedPreferences.edit().clear().commit();
    }
}
