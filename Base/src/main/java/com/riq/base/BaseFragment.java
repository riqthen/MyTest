package com.riq.base;


import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        TextView btnToolbarLeft = (TextView) view.findViewById(R.id.btn_toolbar_left);
        TextView btnToolbarRight = (TextView) view.findViewById(R.id.btn_toolBar_right);
        TextView tvToolbarTitle = (TextView) view.findViewById(R.id.tv_toolBar_title);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
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
        initView();

        //处理内存重启导致的多个Fragment重叠问题
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean("state_save_or_hidden");
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("state_save_or_hidden", isHidden());
        super.onSaveInstanceState(outState);
    }

    protected abstract int getLayoutRes();

    protected abstract void initView();

    /**
     * 获取BaseActivity,可以直接调用BaseActivity中的方法
     */
    private BaseActivity baseActivity;

    public BaseActivity getBaseActivity() {
        if (baseActivity != null) {
            baseActivity = (BaseActivity) getActivity();
        }
        return baseActivity;
    }

    //------------------> Toolbar
    protected void setToolbarTitle(String title) {
        getBaseActivity().setToolbarTitle(title);
    }

    protected void setToolbarLeftItem(@DrawableRes int resId, String text) {
        getBaseActivity().setToolbarLeftItem(resId, text);
    }

    protected void setToolbarRightItem(@DrawableRes int resId, String text) {
        getBaseActivity().setToolbarRightItem(resId, text);
    }

    public void onClickToolbarLeftItem(View v) {
    }

    public void onClickToolbarRightItem(View v) {
    }



    //------------------> ProgressDialog
    public void showProgress(String title, String msg, boolean touchOutsideCancelable, boolean isCancelable) {
        getBaseActivity().showProgress(title, msg, true, isCancelable);
    }

    public void hideProgress() {
        getBaseActivity().hideProgress();
    }


}
