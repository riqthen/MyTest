package com.riq.mylibrary.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.riq.mylibrary.R;


/**
 * Created by riq on 2017/5/11.
 * Toast工具
 * ToastUtils.showToast(this, "");
 * ToastUtils.hideToast();
 */

public class ToastUtils {
    private static Toast toast;

    public static void showToast(Context context, String msg) {
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 200);   //200表示距离屏幕底部的距离
        TextView textView = new TextView(context);
        textView.setText(msg);
        textView.setTextSize(14);
        textView.setTextColor(Color.WHITE); //文字颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackground(context.getResources().getDrawable(R.drawable.toast_bg));    //背景样式
        }
        toast.setView(textView);
        toast.show();
    }

    /**
     * 隐藏Toast
     */
    public static void hideToast() {
        if (null != toast) {
            toast.cancel();
        }
    }
}
