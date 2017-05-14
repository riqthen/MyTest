package com.riq.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by riq on 2017/5/14.
 */

public class ActionUtils {

    /**
     * TODO 显示软键盘
     * (功能未实现)
     *
     * @param activity 当前Activity
     * @param view     接受软键盘输入的视图,光标在该视图上才显示软键盘
     */
    public static void showKeyboard(Activity activity, View view) {
        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
    }

    /**
     * TODO 隐藏软键盘
     *
     * @param activity 当前Activity
     */
    public static void hideKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
