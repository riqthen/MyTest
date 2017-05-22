package com.riq.test;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;

import com.riq.basebottomtablib.BaseBottomTabActivity;
import com.riq.basebottomtablib.BottomTabView;
import com.riq.mylibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseBottomTabActivity {

    @Override
    protected List<BottomTabView.TabItemView> getTabViews() {
        List<BottomTabView.TabItemView> tabItemViews = new ArrayList<>();
        tabItemViews.add(new BottomTabView.TabItemView(this, "", 10, 10, 10, 10
                , 255, Color.rgb(255, 255, 0), R.mipmap.eye_close, R.mipmap.eye_open));
        tabItemViews.add(new BottomTabView.TabItemView(this, "h2", 10, 10, 10, 10
                , 0, Color.argb(255, 0, 0, 255), 0, R.mipmap.eye_open));
        tabItemViews.add(new BottomTabView.TabItemView(this, "h2", 10, 10, 10, 10
                , 0, Color.argb(55, 44, 51, 47), R.mipmap.eye_close, R.mipmap.eye_open));
        return tabItemViews;
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentTab1());
        fragments.add(new FragmentTab2());
        fragments.add(new FragmentTab1());
        return fragments;
    }

    @Override
    public View setCenterView(int iconRes, int iconWidth, int iconHeight, int leftMargin, int rightMargin, boolean isOutTab, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        return super.setCenterView(R.mipmap.ic_launcher_round, 200, 200, 20, 20, true, onClickListener, onLongClickListener);
    }



    @Override
    public void onSecondClick() {
        ToastUtils.showToast(this, "再次点击");
    }


    @Override
    public boolean isVpScrollable() {
        return true;
    }
}
