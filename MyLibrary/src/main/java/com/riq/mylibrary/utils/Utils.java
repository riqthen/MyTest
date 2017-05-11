package com.riq.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 10903 on 2017/4/22.
 */

public class Utils {
    //-------------------   String







    /**
     * TODO 隐藏软键盘
     *
     * @param activity 当前Activity
     */
    public static void hideKeyBoard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * TODO 显示软键盘
     * (功能未实现)
     *
     * @param activity 当前Activity
     * @param view     接受软键盘输入的视图,光标在该视图上才显示软键盘
     */
    public static void showKeyBoard(Activity activity, View view) {
        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
    }


    /**
     * TODO 匹配手机号码
     * 移动号段：139 138 137 136 135 134 147 150 151 152 157 158 159 178 182 183 184 187 188
     * 联通号段：130 131 132 155 156 185 186 145 176
     * 电信号段：133 153 177 173 180 181 189
     * 虚拟运营商号段：170 171
     * <p>
     * 130 131 132 133 134 135 136 137 138 139 145 147 150 151 152 153 155 156
     * 157 158 159 170 171 173 176 177 178 180 181 182 183 184 185 186 187 188 189
     *
     * @param phoneNumber 手机号
     * @return 是否是正确的手机号
     */
    public static boolean isMobilePhone(String phoneNumber) {
        Pattern p = Pattern.compile("^1((3\\d)|(4[57])|(5[^4,\\D])|(7[013678])|(8\\d))\\d{8}$");
        Matcher m = p.matcher(phoneNumber.trim());
        return m.matches();
    }

    /**
     * TODO 获取屏幕宽度
     * 1920
     **/
    public static int getWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * TODO 获取屏幕高度
     * 1080
     **/
    public static int getHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

// TODO: 2017/4/22 时间相关
    /**
     * TODO 时间戳转日期字符串
     *
     * @param timestamp 时间戳（毫秒）
     * @param pattern   Format格式,如：yyyy-MM-dd/yyyymmdd/yyyy-MM-dd HH:mm:ss...
     * @return 时间字符串 格式： yyyy-MM-dd/yyyyMMdd...
     */
    public static String formatTimestamp(long timestamp, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(timestamp));
    }

    public static String formatTimestamp(String timestamp, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(Long.parseLong(timestamp)));
    }


    /**
     * TODO 比较日期大小
     * (只能日期格式) date1 - date2
     *
     * @param date1 日期1，格式yyyy-mm-dd
     * @param date2 日期2，格式yyyy-mm-dd
     * @return 1，大于；    0，等于；   -1，小于；  -2,比较失败.
     */
    public static int compareDate(String date1, String date2) {
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() == dt2.getTime()) {
                return 0;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

// TODO: 2017/4/22 程序版本相关

    /**
     * TODO 获取程序版本名
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * TODO 获取程序版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

// TODO: 2017/4/22 集合相关
    /**
     * TODO ArrayList去除重复
     *
     * @param list
     * @return
     */
    public static ArrayList removeSame(ArrayList list) {
        if (null == list) {
            return list;
        }
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

}
