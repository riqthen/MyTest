package com.riq.base;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by riq on 2017/4/27.
 * Toolbar: 默认隐藏Toolbar,给Toolbar设置图标文字等则会显示Toolbar  !隐藏系统title栏，在布局中include引用toolbar布局文件layout_toolbar.xml
 */

public abstract class BaseActivity extends AppCompatActivity {


    private TextView btnToolbarLeft;
    private TextView tvToolbarTitle;
    private TextView btnToolbarRight;
    private Toolbar toolbar;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        unbinder = ButterKnife.bind(this);
        btnToolbarLeft = (TextView) findViewById(R.id.btn_toolbar_left);
        btnToolbarRight = (TextView) findViewById(R.id.btn_toolBar_right);
        tvToolbarTitle = (TextView) findViewById(R.id.tv_toolBar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initView();
    }

    /**
     * 加载视图R.layout.
     */
    protected abstract int getLayoutRes();

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
    protected void setToolbarLeftItem(@DrawableRes int resId, String text, final OnItemClickListener onItemClickListener) {
        if (toolbar != null) {
            toolbar.setVisibility(View.VISIBLE);
            if (resId > 0) {
                Drawable drawable = getResources().getDrawable(resId);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                btnToolbarLeft.setCompoundDrawablePadding(50);      //设置图标与文字之间的距离
                btnToolbarLeft.setCompoundDrawables(drawable, null, null, null);
            }
            btnToolbarLeft.setText(text);
            if (null != btnToolbarLeft) {
                btnToolbarLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onClick();
                    }
                });
            }
        }
    }

    /**
     * 设置Toolbar右按钮图标和文字
     *
     * @param resId R.mipmap.
     * @param text  文本
     */
    protected void setToolbarRightItem(@DrawableRes int resId, String text, final OnItemClickListener onItemClickListener) {
        if (toolbar != null) {
            toolbar.setVisibility(View.VISIBLE);
            if (resId > 0) {
                Drawable drawable = getResources().getDrawable(resId);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                btnToolbarRight.setCompoundDrawablePadding(50);
                btnToolbarRight.setCompoundDrawables(null, null, drawable, null);
            }
            btnToolbarRight.setText(text);
            if (null != btnToolbarRight) {
                btnToolbarRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onClick();
                    }
                });
            }
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


    public interface OnItemClickListener {
        void onClick();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
