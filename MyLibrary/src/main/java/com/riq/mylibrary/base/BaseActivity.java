package com.riq.mylibrary.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.riq.utilslib.R;

import butterknife.ButterKnife;


/**
 * Created by riq on 2017/4/27.
 * Toolbar: 默认隐藏Toolbar,给Toolbar设置图标文字等则会显示Toolbar  !需要在布局中include引用toolbar布局文件layout_toolbar.xml
 * showToast: 自定义toast,背景文件为toast_bg.xml
 * showProgress: 显示ProgressDialog
 * checkNetworkIsAvailable: 检查是否有网络
 */

public abstract class BaseActivity extends AppCompatActivity {
    {
        BaseApplication.addActivity(this);
    }

    private TextView btnToolbarLeft;
    private TextView tvToolbarTitle;
    private TextView btnToolbarRight;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    /**
     * 加载视图R.layout.
     */
    protected abstract int getLayoutRes();


    /**
     * 找到Toolbar中的组件及配置点击事件
     */
    private void initToolbar() {
        btnToolbarLeft = (TextView) findViewById(R.id.btn_toolbar_left);
        btnToolbarRight = (TextView) findViewById(R.id.btn_toolBar_right);
        tvToolbarTitle = (TextView) findViewById(R.id.tv_toolBar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != btnToolbarLeft) {
            btnToolbarLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickToolbarLeftItem(v);
                }
            });
        }
        if (null != btnToolbarRight) {
            btnToolbarRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickToolbarRightItem(v);
                }
            });
        }
    }

    /**
     * 初始化视图,包括Toolbar
     */
    protected abstract void initView();

    // TODO: 2017/5/2 --------------------------> Toolbar的具体方法

    //以下关于Toolbar的方法均写在initView()中,为空则表示无标题栏

    /**
     * 设置Toolbar标题
     *
     * @param title 标题
     */
    protected void setToolbarTitle(String title) {
        if (toolbar != null) {
            toolbar.setVisibility(View.VISIBLE);
            tvToolbarTitle.setText(title);
        }
    }

    /**
     * 设置Toolbar左按钮图标和文字
     *
     * @param resId 资源Id
     * @param text  文本
     */
    protected void setToolbarLeftItem(@DrawableRes int resId, String text) {
        if (toolbar != null) {
            toolbar.setVisibility(View.VISIBLE);
            if (resId > 0) {
                Drawable drawable = getResources().getDrawable(resId);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                btnToolbarLeft.setCompoundDrawablePadding(50);      //设置图标与文字之间的距离
                btnToolbarLeft.setCompoundDrawables(drawable, null, null, null);
            }
            btnToolbarLeft.setText(text);
        }
    }

    /**
     * 设置Toolbar右按钮图标和文字
     *
     * @param resId R.mipmap.
     * @param text  文本
     */
    protected void setToolbarRightItem(@DrawableRes int resId, String text) {
        if (toolbar != null) {
            toolbar.setVisibility(View.VISIBLE);
            if (resId > 0) {
                Drawable drawable = getResources().getDrawable(resId);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                btnToolbarRight.setCompoundDrawablePadding(50);
                btnToolbarRight.setCompoundDrawables(null, null, drawable, null);
            }
            btnToolbarRight.setText(text);
        }
    }


    /**
     * 设置Toolbar背景颜色
     *
     * @param color Color.RED
     */
    public void setToolbarBgColor(int color) {
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setBackgroundColor(color);
    }

    /**
     * 设置Toolbar背景颜色
     *
     * @param colorStr "#fedffd"
     */
    public void setToolbarBgColor(String colorStr) {
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setBackgroundColor(Color.parseColor(colorStr));
    }

    /**
     * 左按钮的点击事件
     *
     * @param v
     */
    public void onClickToolbarLeftItem(View v) {
    }

    /**
     * 右按钮的点击事件
     *
     * @param v
     */
    public void onClickToolbarRightItem(View v) {
    }

    // TODO: 2017/5/2 --------------------------> Toast
    private Toast toast;

    /**
     * 显示Toast
     *
     * @param msg 需要Toast的信息
     */
    public void showToast(String msg) {
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 200);   //200表示距离屏幕底部的距离
        TextView textView = new TextView(this);
        textView.setText(msg);
        textView.setTextSize(14);
        textView.setTextColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackground(getResources().getDrawable(R.drawable.toast_bg));
        }
        toast.setView(textView);
        toast.show();
    }

    /**
     * 隐藏Toast
     */
    public void hideToast() {
        if (null != toast) {
            toast.cancel();
        }
    }

    // TODO: 2017/5/2 --------------------------> ProgressDialog
    private boolean isActive = false;
    private ProgressDialog progressDialog;

    public void showProgress(String title, String msg, boolean touchOutsideCancelable, boolean isCancelable) {
        //为了表示,只有在Activity存在的时候才show
        if (!isActive) {
            return;
        }
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
//        progressDialog.setCustomTitle(null);
        progressDialog.setTitle(title);
        progressDialog.setMessage(msg); //设置显示信息
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  //设置样式
        progressDialog.setCanceledOnTouchOutside(touchOutsideCancelable);   //点击外部是否可取消
        progressDialog.setCancelable(isCancelable); //点击返回是否可取消
        handler.sendEmptyMessageDelayed(0x1, 180000);   //3分钟后自动关闭该ProgressDialog
        progressDialog.show();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x1)
                hideProgress();
        }
    };

    /**
     * 隐藏ProgressDialog
     */
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }


    // TODO: 2017/5/2 --------------------------> 检查网络是否可用

    public static boolean checkNetworkIsAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        NetworkInfo netWorkInfo = info[i];
                        if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            return true;
                        } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActive = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActive = false;
    }

}
